package com.model;
import com.datastructures.sequential.SinglyLinkedList;
import com.model.subject;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.model.group;
import java.time.LocalTime;
import java.util.HashMap;

public class schedule {

    private SinglyLinkedList<subject> subjects = new SinglyLinkedList<>();

    private SinglyLinkedList<group> groups = new SinglyLinkedList<>();

    private Map<DayOfWeek, subject> schedule = new TreeMap<>(); //Also could be a treemap with keys-value=DayOfWeek-groups
    private String scheduleName;

    public HashMap<subject,group> mapSubjectGroup=new HashMap<>();

    public boolean hasConflict(group newGroup) {
        // Recorremos la lista de grupos actuales en el horario
        for (group existingGroup : groups) {
            // Obtenemos los días de ambos grupos
            DayOfWeek[] diasNuevoGrupo = newGroup.getDays();
            DayOfWeek[] diasGrupoExistente = existingGroup.getDays();

            // Verificamos si hay intersección en los días
            for (DayOfWeek diaNuevo : diasNuevoGrupo) {
                System.out.println("gkgk");
                for (DayOfWeek diaExistente : diasGrupoExistente) {
                    System.out.println(diaExistente);
                    if (diaNuevo.equals(diaExistente)) {
                        System.out.println("mismo dia");
                        // Si coinciden los días, verificamos las horas
                        if (hayConflictoDeHoras(newGroup, existingGroup)) {
                            return true; // Si hay conflicto en horas, retornamos true
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hayConflictoDeHoras(group g1, group g2) {
        LocalTime inicioG1 = g1.getStartTime();  // Método para obtener la hora de inicio
        LocalTime finG1 = g1.getEndTime();       // Método para obtener la hora de fin
        LocalTime inicioG2 = g2.getStartTime();
        LocalTime finG2 = g2.getEndTime();

        // Verificamos si los rangos de horas se solapan
        return inicioG1.isBefore(finG2) && inicioG2.isBefore(finG1);
    }

    public void addSubject(subject subj, group grp) {
        // Verificar si hay conflictos de horario antes de agregar la materia
        if (!hasConflict(grp)) {
            subjects.pushBack(subj);
            groups.pushBack(grp);
            mapSubjectGroup.put(subj,grp);
            // Lógica adicional para agregar el grupo y el horario
            System.out.println("Asignatura agregada: " + subj.getName());
        } else {
            System.out.println("Conflicto de horario para: " + subj.getName());
        }
    }

    public HashMap<subject, group> getMapSubjectGroup() {
        return mapSubjectGroup;
    }

    public static void main(String[] args) {
        com.model.schedule schedule1 = new schedule();
        subject subject = new subject(4944, "calculo", 3, "optativa");
        subject subject2 = new subject(944, "algebra", 3, "optativa");
        group g1 = new group(1, "alfonos", "ingenería", 7, 9, "no se", "lunes-miercoles");
        group g2 = new group(1, "alfonos", "ingenería", 7, 9, "no se", "jueves");
        subject.addGroup(g1);
        subject2.addGroup(g2);
        schedule1.addSubject(subject, g1);
        schedule1.addSubject(subject2,g2);
    }

    public subject findSubjectByName(String subjectName) {
        for (subject subj : mapSubjectGroup.keySet()) {
            if (subj.getName().equals(subjectName)) { // Comparar nombres
                return subj; // Retornar la subject asociada
            }
        }
        return null; // Si no se encuentra, retornar null
    }

    public group getGroupBySubject(subject currentSubject) {
        return mapSubjectGroup.get(currentSubject);
    }
}
