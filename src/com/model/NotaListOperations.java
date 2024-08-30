class NotaListOperations {

    private SinglyLinkedList<nota> listaDeNotas;

    public NotaListOperations(SinglyLinkedList<nota> listaDeNotas) {
        this.listaDeNotas = listaDeNotas;
    }

    private double redondear(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    public double promedio(){
        double mean = (notaAcumulada()*100)/sumaPorcentajes();
        return redondear(mean);
    }
    
    public double notaAcumulada() {
        SinglyLinkedList.Node<nota> current = listaDeNotas.topFrontNode();

        if (current == null) {
            System.out.println("List is empty");
            return 0; // Retornar 0 si la lista está vacía
        }

        double mean = 0;
        while (current != null) {
            nota currentNota = current.key;
            mean += currentNota.getValor() * currentNota.getPorcentaje() * 0.01;
            current = current.next;
        }
        return redondear(mean);
    }

    public double sumaPorcentajes() {
        SinglyLinkedList.Node<nota> current = listaDeNotas.topFrontNode();
        double sumaPorcentajes = 0;

        while (current != null) {
            sumaPorcentajes += current.key.getPorcentaje();
            current = current.next;
        }
        return sumaPorcentajes;
    }

    public double[] notaMinima() {
        double[] notaMinArray = new double[2];
        double sumaPorcentajes = sumaPorcentajes(); // Evitar llamar dos veces al mismo método

        if (sumaPorcentajes >= 0 && sumaPorcentajes <= 99) {
            double diferenciaPorcentaje = 100 - sumaPorcentajes;
            double notaMin = (3 - notaAcumulada()) / (diferenciaPorcentaje * 0.01);
            notaMinArray[1] = diferenciaPorcentaje;
            notaMinArray[0] = redondear(notaMin);
            if (notaMinArray[0] < 0){
                notaMinArray[0] = 0;
            }
        } else {
            notaMinArray[0] = 0;
            notaMinArray[1] = 0;
        }
        return notaMinArray;
    }
}
