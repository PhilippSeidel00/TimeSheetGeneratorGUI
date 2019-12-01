package checker;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import org.junit.Test;

import data.Employee;
import data.Entry;
import data.TimeSheet;
import data.Profession;
import data.TimeSpan;
import data.WorkingArea;

public class MiLoGCheckerCheckTest {

    ////Placeholder for documentation construction
    private static final Employee EMPLOYEE = new Employee("Max Mustermann", 1234567);
    private static final Profession PROFESSION = new Profession("Fakultät für Informatik", WorkingArea.UB, new TimeSpan(40, 0), 10.31);
    private static final YearMonth YEAR_MONTH = YearMonth.of(2019, Month.NOVEMBER);
    private static final TimeSpan zeroTs = new TimeSpan(0, 0);
    
    @Test
    public void testSingleEntryValidDocumentation() throws CheckerException {
        ////Test values
        TimeSpan start = new TimeSpan(8, 0);
        TimeSpan end = new TimeSpan(12, 0);
        TimeSpan pause = zeroTs;
        LocalDate date = LocalDate.of(2019, 11, 22); //Valid working day
        
        ////Checker initialization
        Entry entry = new Entry("Test", date, start, end, pause);
        Entry[] entries = {entry};
        TimeSheet fullDoc = new TimeSheet(EMPLOYEE, PROFESSION, YEAR_MONTH, entries, zeroTs, zeroTs, zeroTs);
        MiLoGChecker checker = new MiLoGChecker(fullDoc);
        
        ////Assertions
        assertEquals(CheckerReturn.VALID, checker.check());
        assertTrue(checker.getErrors().isEmpty());
    }
    
    @Test
    public void testSingleEntryValidButHoliday() throws CheckerException {
        ////Test values
        TimeSpan start = new TimeSpan(8, 0);
        TimeSpan end = new TimeSpan(12, 0);
        TimeSpan pause = zeroTs;
        LocalDate date = LocalDate.of(2019, 12, 25); //Holiday
        
        ////Checker initialization
        Entry entry = new Entry("Test", date, start, end, pause);
        Entry[] entries = {entry};
        TimeSheet fullDoc = new TimeSheet(EMPLOYEE, PROFESSION, YEAR_MONTH, entries, zeroTs, zeroTs, zeroTs);
        MiLoGChecker checker = new MiLoGChecker(fullDoc);
        
        ////Assertions
        assertEquals(CheckerReturn.INVALID, checker.check());
        assertTrue(checker.getErrors().stream().anyMatch(item -> item.getErrorMessage().equals(MiLoGChecker.CheckerErrorMessage.TIME_HOLIDAY.getErrorMessage())));
    }
    
    @Test
    public void testSingleEntryValidButSunday() throws CheckerException {
        ////Test values
        TimeSpan start = new TimeSpan(8, 0);
        TimeSpan end = new TimeSpan(12, 0);
        TimeSpan pause = zeroTs;
        LocalDate date = LocalDate.of(2019, 12, 1); //Sunday
        
        ////Checker initialization
        Entry entry = new Entry("Test", date, start, end, pause);
        Entry[] entries = {entry};
        TimeSheet fullDoc = new TimeSheet(EMPLOYEE, PROFESSION, YEAR_MONTH, entries, zeroTs, zeroTs, zeroTs);
        MiLoGChecker checker = new MiLoGChecker(fullDoc);
        
        ////Assertions
        assertEquals(CheckerReturn.INVALID, checker.check());
        assertTrue(checker.getErrors().stream().anyMatch(item -> item.getErrorMessage().equals(MiLoGChecker.CheckerErrorMessage.TIME_SUNDAY.getErrorMessage())));
    }
    
    @Test
    public void testSingleEntryValidButSundayAndHoliday() throws CheckerException {
        ////Test values
        TimeSpan start = new TimeSpan(8, 0);
        TimeSpan end = new TimeSpan(12, 0);
        TimeSpan pause = zeroTs;
        LocalDate date = LocalDate.of(2022, 12, 25); //Sunday and holiday
        
        ////Checker initialization
        Entry entry = new Entry("Test", date, start, end, pause);
        Entry[] entries = {entry};
        TimeSheet fullDoc = new TimeSheet(EMPLOYEE, PROFESSION, YEAR_MONTH, entries, zeroTs, zeroTs, zeroTs);
        MiLoGChecker checker = new MiLoGChecker(fullDoc);
        
        ////Assertions
        assertEquals(CheckerReturn.INVALID, checker.check());
        assertTrue(checker.getErrors().stream().anyMatch(item -> item.getErrorMessage().equals(MiLoGChecker.CheckerErrorMessage.TIME_SUNDAY.getErrorMessage())));
    }
}