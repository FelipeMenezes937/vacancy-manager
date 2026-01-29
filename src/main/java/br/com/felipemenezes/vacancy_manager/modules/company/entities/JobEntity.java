package br.com.felipemenezes.vacancy_manager.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "job")
public class JobEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String description;
  private String benefits;
  private String level;

  @ManyToOne() // pq eu posso ter muitos jobs para 1 company
  @JoinColumn(name = "company_id")
  private CompanyEntity companyEntity;

  @Column(name = "company_id")
  private UUID company_id;

  @CreationTimestamp
  private LocalDateTime created_at;
}
