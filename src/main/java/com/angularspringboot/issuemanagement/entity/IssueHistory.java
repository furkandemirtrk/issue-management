package com.angularspringboot.issuemanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="issue_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class IssueHistory extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JoinColumn(name = "issue_id")
  @ManyToOne(optional = true,fetch = FetchType.LAZY)
  private Issue issue;

  @JoinColumn(name = "assignee_user_id")
  @ManyToOne(optional = true,fetch = FetchType.LAZY)
  private User assignee;

  @Column(name = "issue_status")
  @Enumerated(value = EnumType.STRING)
  private IssueStatus issueStatus;

  @Column(name = "details", length = 4000)
  private String details;


  @Column(name="date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;
}
