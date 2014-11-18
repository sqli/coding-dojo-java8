package com.sqli.fi;

import com.sqli.domain.User;

/**
 * InterfaceFonctionelle est déclarée comme interface fonctionelle. Ceci n'a
 * aucun effet particulier sur le code, il permet juste au compilateur de
 * vérifier que l'interface rempli bien les critères d'une interface
 * fonctionelle.
 */
@FunctionalInterface
public interface InterfaceFonctionelle {

	/**
	 * Une interface fonctionelle ne doit posséder qu'une seule méthode
	 * abstraite, comme c'est le cas ici avec cette méthode accept. Nous pouvons
	 * utiliser cette interface comme paramètre de méthode est l'instancier avec
	 * une lambda. La lambda sera en fait le corp de la méthode accept.
	 */
	void accept(User u1, User u2, User u3);

	/**
	 * Java 8 apporte également les méthodes default dans les interfaces. Ce
	 * sont des méthodes non abstraites qui proposent une implémentation par
	 * défaut, surchargeables (ou pas) par les classes qui implémentent ces
	 * interfaces.
	 * Java 8 apporte donc l'héritage multiple d'implémentation.
	 */
	public default void print(User u) {
		System.out.println(u.getNom());
	}

}
