package pl.online_clinic_management.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"medicalNoteId", "content"})
@ToString(of = {"medicalNoteId", "content", "appointment"})
public class MedicalNote {

    Long medicalNoteId;
    String content;
    Appointment appointment;
}
