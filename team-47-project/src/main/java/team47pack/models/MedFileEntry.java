package team47pack.models;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Entity
@Table(name="med_file_en")
public class MedFileEntry {
    @Id
    @SequenceGenerator(name = "med_en_id_seq", sequenceName = "med_en_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_en_id_seq")
    Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id", nullable = true)
    private Diagnosis diagnosis;
    

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = true)
    private Doctor doctor;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "med_prescription",
            joinColumns = @JoinColumn(name = "med_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "prescription_id", referencedColumnName = "id"))
    private List<PrescriptionVerification> prescriptions = new ArrayList<>();

    @Column(name = "description", nullable = false, unique = false)
    private String description;
    

	@Column
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date date;

    public MedFileEntry() {
    }

    public MedFileEntry(Long id, Diagnosis diagnosis, List<Prescription> prescriptions, String desc, Patient patient, Doctor doc) {
        this.id = id;
        this.diagnosis = diagnosis;

        List<PrescriptionVerification> pres = new ArrayList<>();
        for (Prescription p : prescriptions) {
            pres.add(new PrescriptionVerification(patient, doc, p));
        }

        this.prescriptions = pres;
        this.description = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public List<PrescriptionVerification> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionVerification> prescriptions) {
        this.prescriptions = prescriptions;
    }

	public Doctor getDoctor() {
		return doctor;
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
