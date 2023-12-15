package Coches;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Puente {
    private int cochesNorte;
    private int cochesSur;

    private Lock lock = new ReentrantLock();
    private Condition esperaNorte = lock.newCondition();
    private Condition esperaSur = lock.newCondition();

    public Puente() {
        cochesNorte = 0;
        cochesSur = 0;
    }

    public void entrarDesdeElNorte() {
        lock.lock();
        try {
            while (cochesSur > 0) {
                esperaNorte.await();
            }
            cochesNorte++;
            System.out.println("Coche del Norte entrando. Coches del Norte en el puente: " + cochesNorte);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void salirDesdeElNorte() {
        lock.lock();
        try {
            cochesNorte--;
            System.out.println("Coche del Norte saliendo. Coches del Norte en el puente: " + cochesNorte);
            if (cochesNorte == 0) {
                esperaSur.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entrarDesdeElSur() {
        lock.lock();
        try {
            while (cochesNorte > 0) {
                esperaSur.await();
            }
            cochesSur++;
            System.out.println("Coche del Sur entrando. Coches del Sur en el puente: " + cochesSur);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void salirDesdeElSur() {
        lock.lock();
        try {
            cochesSur--;
            System.out.println("Coche del Sur saliendo. Coches del Sur en el puente: " + cochesSur);
            if (cochesSur == 0) {
                esperaNorte.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
