package com.sqli.fp;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sqli.domain.User;
import com.sqli.fp.util.UserUtil;

public class UserService {

	/**
	 * Ecris dans la console une chaine contenant tous les noms des personnes
	 * majeures parmis la liste pass�e en param�tre, s�par�s par une "," Si le
	 * nom est vide, il ne doit pas apparaitre dans la liste. Les utilisateurs
	 * doivent �tre tri�s � la base par leur nom, pr�nom et age dans cet ordre.
	 * 
	 * @param users
	 */
	public void printNomsPeronnesMajeures(List<User> users) {
		// Timing de la m�thode en utilisant la nouvelle API de manipulation du
		// temps, issue de Joda time.
		// Un Instant repr�sente un point dans le temps.
		Instant start = Instant.now();
		// On va d�crire le r�sultat souhait� � travers l'API Stream
		List<String> names = users
		// Cr�ation d'un stream � aprtir d'une List. NB : il est aussi
		// possible de cr�er un parallelStream() qui va automatiquement
		// parall�liser les traitements d�cris ensuite.
				.stream()
				// Ne garder que les personnes majeures : c'est l'�tape de
				// filtrage
				// filter(p) prend un Predicate p qui est une m�thode prenant un
				// T en param�tre et renvoyant un Boolean.
				// Nous instancions ce Predicate � travers une Lambda
				// expression. (Voir l'interface InterfaceFonctionnelle de ce
				// coding dojo pour voir comment on peut d�clarer une interface
				// instanciable avec une Lambda expression)
				.filter(u -> u.getAge() >= 18)
				// Tri des User majeurs par nom, prenom et age. Ici, au lieu
				// d'utiliser une lambda expression pour renvoyer le champ que
				// l'on veut trier (on pourrait �crire par exemple
				// comparing(u->u.getNom()), on utilise la r�f�rence de m�thode
				// Lorsque un User u est envoy� � comparing() la m�thode getNom
				// sera appel� sur ce u.
				.sorted(Comparator.comparing(User::getNom)
						.thenComparing(User::getPrenom)
						.thenComparing(User::getAge))
				// On ne souhaite garder que le nom des User, ce qui revient �
				// dire qu'on souhaite transformer un User en un String. C'est
				// l'�tape de mapping.
				// map() prend une Function qui prend un T en param�tre et
				// renvoi un R en sorti, o� T est le type du stream et R un type
				// quelconque, d�termin� par le type du return de la Function.
				// NB : Lorsque une seule ligne suffit dans une lambda
				// expression, on peut se passer des {} et du return, comme suit
				.map(user -> user.getNom())
				// Transformation du Stream en List pour exploitation future.
				// collect() est une m�thode dite terminale, car elle ne renvoi
				// pas de Stream.
				// C'est la m�thode qui va d�clencher le d�but du traitement,
				// tous les appels pr�c�dents n'ont rien fait jusqu'ici, c'est
				// la Lazy evaluation.
				// C'est pour cela qu'on parle de code d�claratif, car on ne
				// fait que d�crire un r�sultat souhait�, et Java s'occupe des
				// d�tails d'ex�cution tout seul (et optimis�s en plus, sans
				// qu'on ai besoin de s'en pr�occuper)
				.collect(Collectors.toList());

		// Fin du timing de m�thode
		Instant end = Instant.now();
		// Utiliastion de Duration, toujours extrait de Joda time qui repr�sente
		// une dur�e entre deux Instant.
		// Il y a d'autres m�thodes to*** avec d'autres unit�s.
		System.out.println(Duration.between(start, end).toMillis());
		// Pas de boucle for pour ajouter une "," entre tous les noms de la
		// liste, mais la nouvelle m�thode join() de String qui met la chaine
		// pass� en param�tre en tous les �l�ments de l'Iterable pass� en second
		// param�tre.
		System.out.println(String.join(",", names));
	}

	
	public static void main(String[] args) {
		UserService us = new UserService();
		us.printNomsPeronnesMajeures(UserUtil.getHugeListOfUser());
	}

}
