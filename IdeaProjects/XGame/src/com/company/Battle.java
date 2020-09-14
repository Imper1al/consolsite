package com.company;


import java.util.concurrent.TimeUnit;

public class Battle {

    Registration registration = new Registration();
    Weapon weapon = new Weapon();
    Weapon weapon1 = new Weapon();

    public void GetInfo() {

        registration.Reg();

    }

    public void Whait(){

        try {
            System.out.println("\nНачало боя!!!\n");
            for (int i = 5; i >=1; i --) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    Thread tr1 = new Thread(new Runnable() {
        @Override
        public void run() {
            weapon.Hit(registration.firstName, registration.gunnameCT);
            if (weapon1.gameend == true) {
                System.out.println("Победил игрок " + weapon1.name);
                Thread r = tr1;
                if (r != null)
                    r.interrupt();
            }
        }
    });
    Thread tr2 = new Thread(new Runnable() {
        @Override
        public void run() {
            weapon1.Hit(registration.secondName, registration.gunnameTR);
            if (weapon.gameend == true) {
                System.out.println("Победил игрок " + weapon.name);
                Thread r = tr2;
                if (r != null)
                    r.interrupt();
            }
        }
    });
}
