package fr.formation.proxi4.metier.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe modélisant une réponse à un sondage de l'application.
 * 
 * @author Adminl
 *
 */
@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private Boolean isPositive;

	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	@ManyToOne
	private Survey survey;

	@Column
	private LocalDate entryDate;

	@Column
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
