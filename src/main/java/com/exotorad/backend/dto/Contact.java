package com.exotorad.backend.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class representing a Contact in the database. This class maps to a
 * database table and is used for persisting and retrieving contact information.
 */

@Data // Generates boilerplate code like getters, setters, equals, hashCode, and
		// toString using Lombok.
@Entity // Marks this class as a JPA entity (maps to a table in the database).
public class Contact {
	@Id // Marks this field as the primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the ID will be generated automatically using
														// the database's identity column.
	private Integer id;
	private String phoneNumber;
	private String email;
	private Integer linkedId;
	// Enum to represent the link precedence of the contact (e.g., primary or
	// secondary).
	private LinkPrecedence linkPrecedence;
	@CreationTimestamp // Automatically sets the timestamp when the contact is first created.
	private Timestamp createdAt;
	@UpdateTimestamp // Automatically updates the timestamp when the contact is modified.
	private Timestamp updatedAt;
	@CurrentTimestamp // Records the timestamp when the contact is marked as deleted (if applicable).
	private Timestamp deletedAt;

}
