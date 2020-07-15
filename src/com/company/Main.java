package com.company;

import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

//        new Thread(() ->{ System.out.println("Printing from the runnable");
//            System.out.println("line 2");
//            System.out.format("this is line %d\n", 3);
//        }).start();

        Employee john = new Employee("john doe", 30);
        Employee tim = new Employee("tim buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Hank Snow",22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employees.get(1));
//        System.out.println(lastName);

        Function<Employee, String> getFirstName = (Employee employee) ->{
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

//        String firstName = getFirstName.apply(employees.get(1));
//        System.out.println(firstName);

        Random random1 = new Random();
        for (Employee employee : employees
             ) {
            if(random1.nextBoolean()){
                System.out.println(getName(getFirstName, employee));
            }else {
                System.out.println((getName(getLastName, employee)));
            }
        }

        Function<Employee, String> upperCase= employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) ->{
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        IntUnaryOperator incBy5 = i ->+5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);

        c1.andThen(c2).accept("Hello world");

    }

    private static String getName(Function<Employee, String> getName, Employee employee){
     return getName.apply(employee);
    }


    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition){
        System.out.println(ageText);
        System.out.println("========================");
       for(Employee employee: employees){
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }
    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1,s2);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat{
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass{
    public String doSomething(){
        int i = 0;

            UpperConcat uc = (s1, s2)-> {
                System.out.println("The lambda expression's class is " + getClass().getSimpleName());
                String result = s1.toUpperCase() + s2.toUpperCase();
                return result;
            };

            System.out.println("The AnotherClass class' name is " + getClass().getSimpleName());
            return Main.doStringStuff(uc,"String1", "String2");
    }
}

