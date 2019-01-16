package fr.formation.proxi4.metier.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe modélisant un sondage de l'application.
 * 
 * @author Adminl
 *
 */
@Entity
@Table(name = "survey")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "answers" })
public class Survey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private LocalDate startDate;

	@Column
	private LocalDate tempEndDate;

	@Column
	private LocalDate endDate;

	@OneToMany(mappedBy = "survey")
	private List<Answer> answers;

	// @Transient permet d'indiquer à Hibernate de ne pas enregistrer cet attribut
	// en base de données. 
	@Transient
	private Integer positiveCount;

	@Transient
	private Integer negativeCount;

	public Survey() {
		super();
	}

	public Survey(LocalDate startDate, LocalDate tempEndDate) {
		super();
		this.startDate = startDate;
		this.tempEndDate = tempEndDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getTempEndDate() {
		return tempEndDate;
	}

	public void setTempEndDate(LocalDate tempEndDate) {
		this.tempEndDate = tempEndDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(Integer positiveCount) {
		this.positiveCount = positiveCount;
	}

	public Integer getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(Integer negativeCount) {
		this.negativeCount = negativeCount;
	}

}
