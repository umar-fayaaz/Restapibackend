package com.exotorad.backend.dto;

/**
 * Enum representing the precedence of a link in the system.
 * 
 * <ul>
 * <li><b>PRIMARY</b> - Indicates the main or original link in a
 * relationship.</li>
 * <li><b>SECONDARY</b> - Represents a subordinate or derived link associated
 * with the primary.</li>
 * </ul>
 * 
 * This enum is typically used to determine the priority of links when managing
 * relationships between entities.
 */
public enum LinkPrecedence {
	PRIMARY, // Main or original link
	SECONDARY // Subordinate or associated link

}
