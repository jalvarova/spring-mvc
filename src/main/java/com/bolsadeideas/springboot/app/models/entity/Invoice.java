package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String description;

	private String observation;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate;

	
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "invoice_id")
	private List<ItemInvoice> items;

	public Invoice() {
		this.items = new ArrayList<ItemInvoice>();
	}

	@PrePersist
	public void prePersist() {
		createDate = new Date();
	}

	public void addItemInvoice(ItemInvoice item) {
		this.items.add(item);
	}

	public Double getTotal() {
		return items
				.stream()
				.collect(Collectors.summingDouble(ItemInvoice::calculateAmount));
	}

	@XmlTransient
	public Client getClient() {
		return client;
	}
	private static final long serialVersionUID = 1L;
}
