package com.rocket.birthday.model.member;

import com.rocket.birthday.model.member.vo.ProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "members",
    uniqueConstraints = @UniqueConstraint(
        name="member_unique_column",
        columnNames = { "provider_id", "provider_type", "email", "name"}))
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "provider_id")
  private String providerId;

  @Column(name = "provider_type")
  @Enumerated(EnumType.STRING)
  private ProviderType providerType;

  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String name;
  @Column(name = "profile_image_url")
  private String profileImageUrl;

  @Column(nullable = false)
  private LocalDate birthday;

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  public Member update(String name, String profileImageUrl, LocalDate birthday) {
    this.name = name;
    this.profileImageUrl = profileImageUrl;
    this.birthday = birthday;
    return this;
  }
}
