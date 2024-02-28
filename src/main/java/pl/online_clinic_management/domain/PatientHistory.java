package pl.online_clinic_management.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "patientHistoryId")
@ToString(of = {"patientHistoryId", "date", "notes"})
public class PatientHistory {
    Long patientHistoryId;
    String date;
    String notes;
    Patient patient;
}
