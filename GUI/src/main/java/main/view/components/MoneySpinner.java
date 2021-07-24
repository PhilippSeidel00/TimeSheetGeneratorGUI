package main.view.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;

import java.time.LocalTime;

/**
 * MoneySpinner spinning money in format xxxx.yy
 * Heavily influenced by stack overflow user James_D
 *
 * @author Philipp Seidel, James_D
 * @version 1.0
 */
public class MoneySpinner extends Spinner<String> {

    private static final String BALANCE_FORMAT = "%s%d.%s%d€";

    enum Mode {

        EURO {
            @Override
            int increment(int balance, int steps) {
                return balance + 100 * steps;
            }

            @Override
            void select(MoneySpinner spinner) {
                int index = spinner.getEditor().getText().indexOf('.');
                spinner.getEditor().selectRange(0, index);
            }
        },
        CENT {
            @Override
            int increment(int balance, int steps) {
                return balance + steps;
            }

            @Override
            void select(MoneySpinner spinner) {
                int hrIndex = spinner.getEditor().getText().indexOf('.');
                spinner.getEditor().selectRange(hrIndex+1, hrIndex+3);
            }
        };
        abstract int increment(int balance, int steps);
        abstract void select(MoneySpinner spinner);
        int decrement(int balance, int steps) {
            return increment(balance, -steps);
        }
    }

    private final ObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.EURO) ;

    public ObjectProperty<Mode> modeProperty() {
        return mode;
    }

    public final Mode getMode() {
        return modeProperty().get();
    }

    public final void setMode(Mode mode) {
        modeProperty().set(mode);
    }

    public MoneySpinner() {
        setEditable(true);

        TextFormatter<LocalTime> textFormatter = new TextFormatter<>(c -> {
            String newText = c.getControlNewText();
            if (newText.matches("[0-9]*.[0-9]{0,2}€")) {
                return c ;
            }
            return null ;
        });

        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory<>() {


            {
                setValue("00.00€");
            }

            @Override
            public void decrement(int steps) {
                var newBalance = mode.get().decrement(getBalance(), steps);
                setValue(formatBalance(Math.max(newBalance, 0)));
            }

            @Override
            public void increment(int steps) {
                setValue(formatBalance(mode.get().increment(getBalance(), steps)));
            }

        };

        this.setValueFactory(valueFactory);
        this.getEditor().setTextFormatter(textFormatter);

        this.getEditor().addEventHandler(InputEvent.ANY, e -> {
            int caretPos = this.getEditor().getCaretPosition();
            int hrIndex = this.getEditor().getText().indexOf('.');
            if (caretPos <= hrIndex) {
                mode.set( Mode.EURO );
            } else {
                mode.set( Mode.CENT );
            }
        });

        mode.addListener((obs, oldMode, newMode) -> newMode.select(this));
    }

    /**
     * get current balance in cents
     * @return the current balance
     */
    public final int getBalance() {
        return Integer.parseInt(getValue().replace(".", "").replace("€", ""));
    }

    private static String formatBalance(int balance) {
        var euro = balance / 100;
        var cent = balance % 100;
        return String.format(BALANCE_FORMAT,(euro < 10) ? "0" : "", euro, (cent < 10) ? "0" : "", cent);
    }
}
