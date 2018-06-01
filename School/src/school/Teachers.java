/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package school;

import java.util.*;

public class Teachers {

    private String name;
    private int height;
    private int weight;
    public static int numTeachers;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    Calendar now = Calendar.getInstance();
    
    public Teachers(String _name, int _GradeLvl, int _height, int _weight, int _birthYear, int _birthMonth, int _birthDay){
    name = _name;
    height = _height;
    weight = _weight;
    birthYear = _birthYear;
    birthMonth = _birthMonth;
    birthDay = _birthDay;
    numTeachers++;
}
    public String getName()
    {
        return(name);
    }
    public int getHeight()
    {
        return(height);
    }
    public int getWeight()
    {
        return(weight);
    }
    public int getBirthYear()
    {
        int nowYear = now.get(Calendar.YEAR);
        int ageYear = nowYear - birthYear;
        return(ageYear + getBirthMonth());
    }
    public int getBirthMonth()
    {
        int nowMonth = now.get(Calendar.MONTH) + 1;
        if (nowMonth < birthMonth)
        {
            return(0);
        }
        else
        {
            return(1);
        }
    }
    public int getBirthDay()
    {
        int nowDay = now.get(Calendar.DAY_OF_MONTH);
        int ageDay = nowDay - birthDay;
        return(ageDay);
    }
    public void getTeacherInfo()
    {
        if (name != null)
        {
        System.out.println("Name is: " + getName());
        }
        else
        {
        System.out.println("This student has no name");
        }
        System.out.println("Height is: " + getHeight() + " feet");
        System.out.println("Age is: " + getBirthYear() + " years old");
        System.out.println("Weight is: " + getWeight() + " pounds");
        
        System.out.println("___________________" + "\n");
    }
}
