package com.ogorodnik.reflection;

import java.lang.reflect.InvocationTargetException;

public class ReflectionTask {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
// Метод принимает класс и возвращает созданный объект этого класса
        Object a = A.createObject(A.class);

//Метод принимает object и вызывает у него все методы без параметров
        Object b = new A();
        A.callFieldsWithoutParams(b);

//Метод принимает object и выводит на экран все сигнатуры методов в который есть final
        A.printMethodFinalSignatures(b);

//Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
        class B extends A{
        }
        class C extends B implements testInterface1, testInterface2{
        }
        C.printParentsAndInterfaces(C.class);

//Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
        A c = new A(15, true, 5.2, "Alex");
        A.setPrivateFieldsToDefaults(c);
    }
}
