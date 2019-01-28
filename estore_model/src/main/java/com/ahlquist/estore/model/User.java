/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Douglas Ahlquist
 *
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -5507368169886902887L;

	private String firstname;
	private String lastname;

	private String email;

	private String username;

	// this is the current password. prior password should be saved else where
	private String password;

	// this login token should be saved in all Restful request headers, if not
	// present,
	// the request should fail and eventually redirected to a login. For
	// purposes of this
	// implementation only the attribute is here.
	private String loginToken;

}
