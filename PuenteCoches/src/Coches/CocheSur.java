package Coches;

class CocheSur extends Thread {
    private Puente puente;

    public CocheSur(Puente puente) {
        this.puente = puente;
    }

    public void run() {
        while (true) {
            // Simula tiempo de espera antes de intentar cruzar el puente
            try {
                Thread.sleep((long) (Math.random() * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            puente.entrarDesdeElSur();

            // Simula el tiempo que tarda en cruzar el puente
            try {
                Thread.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            puente.salirDesdeElSur();
        }
    }
}

