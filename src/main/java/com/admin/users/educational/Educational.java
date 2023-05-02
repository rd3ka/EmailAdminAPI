package com.admin.users.educational;

import com.admin.users.Users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Educational extends Users {

    private final Double Height,
            Weight;
    private final char Gender;
    private String InstituteName;
    private final int Age;
    public Educational(String FirstName, String LastName, String InstituteName,
                       Double Height, Double Weight,
                       char Gender, long UNI,
                       String DateOfBirth) throws ParseException {
        super(FirstName, LastName, DateOfBirth, UNI);
        this.Age = CalculateAge();
        this.Gender = Gender;
        this.Height = Height;
        this.Weight = Weight;
        this.InstituteName = InstituteName;
    }

    public Double getHeight() {
        return Height;
    }

    public Double getWeight() {
        return Weight;
    }

    public char getGender() {
        return Gender;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }

    public int getAge() {
        return Age;
    }

    private synchronized int CalculateAge() {
        Calendar origin = getCalendar(super.getDateOfBirth());
        Calendar now = getCalendar(new SimpleDateFormat("dd/MM/yyyy").get2DigitYearStart());

        int difference = now.get(YEAR) - origin.get(YEAR);

        if (origin.get(MONTH) > now.get(MONTH) ||
                (origin.get(MONTH) == now.get(MONTH) &&
                        origin.get(Calendar.DATE) > now.get(Calendar.DATE)))
            difference--;
        return difference;
    }

    private synchronized Calendar getCalendar(Date date) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        /*^ will have to change TimeZone for varying domicile, country, region and nationality */
        c.setTime(date);
        return c;
    }

    protected synchronized long GenerateUniqueID() {
        String root = String.valueOf(this.InstituteName
                .hashCode());
        String mid  = String.valueOf(super.getDateOfBirth()
                .hashCode());
        String end  = String.valueOf(super.getFirstName()
                .hashCode());
        return Long.parseLong(root + mid + end);
    }

}
