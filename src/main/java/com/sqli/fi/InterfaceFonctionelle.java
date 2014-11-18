package com.sqli.fi;

import com.sqli.domain.User;

/**
 * InterfaceFonctionelle est d�clar�e comme interface fonctionelle. Ceci n'a
 * aucun effet particulier sur le code, il permet juste au compilateur de
 * v�rifier que l'interface rempli bien les crit�res d'une interface
 * fonctionelle.
 */
@FunctionalInterface
public interface InterfaceFonctionelle {

	/**
	 * Une interface fonctionelle ne doit poss�der qu'une seule m�thode
	 * abstraite, comme c'est le cas ici avec cette m�thode accept. Nous pouvons
	 * utiliser cette interface comme param�tre de m�thode est l'instancier avec
	 * une lambda. La lambda sera en fait le corp de la m�thode accept.
	 */
	void accept(User u1, User u2, User u3);

	/**
	 * Java 8 apporte �galement les m�thodes default dans les interfaces. Ce
	 * sont des m�thodes non abstraites qui proposent une impl�mentation par
	 * d�faut, surchargeables (ou pas) par les classes qui impl�mentent ces
	 * interfaces.
	 * Java 8 apporte donc l'h�ritage multiple d'impl�mentation.
	 */
	public default void print(User u) {
		System.out.println(u.getNom());
	}

}
