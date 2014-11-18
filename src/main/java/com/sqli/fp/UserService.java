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
	 * majeures parmis la liste passée en paramètre, séparés par une "," Si le
	 * nom est vide, il ne doit pas apparaitre dans la liste. Les utilisateurs
	 * doivent être triés à la base par leur nom, prénom et age dans cet ordre.
	 * 
	 * @param users
	 */
	public void printNomsPeronnesMajeures(List<User> users) {
		// Timing de la méthode en utilisant la nouvelle API de manipulation du
		// temps, issue de Joda time.
		// Un Instant représente un point dans le temps.
		Instant start = Instant.now();
		// On va décrire le résultat souhaité à travers l'API Stream
		List<String> names = users
		// Création d'un stream à aprtir d'une List. NB : il est aussi
		// possible de créer un parallelStream() qui va automatiquement
		// paralléliser les traitements décris ensuite.
				.stream()
				// Ne garder que les personnes majeures : c'est l'étape de
				// filtrage
				// filter(p) prend un Predicate p qui est une méthode prenant un
				// T en paramètre et renvoyant un Boolean.
				// Nous instancions ce Predicate à travers une Lambda
				// expression. (Voir l'interface InterfaceFonctionnelle de ce
				// coding dojo pour voir comment on peut déclarer une interface
				// instanciable avec une Lambda expression)
				.filter(u -> u.getAge() >= 18)
				// Tri des User majeurs par nom, prenom et age. Ici, au lieu
				// d'utiliser une lambda expression pour renvoyer le champ que
				// l'on veut trier (on pourrait écrire par exemple
				// comparing(u->u.getNom()), on utilise la référence de méthode
				// Lorsque un User u est envoyé à comparing() la méthode getNom
				// sera appelé sur ce u.
				.sorted(Comparator.comparing(User::getNom)
						.thenComparing(User::getPrenom)
						.thenComparing(User::getAge))
				// On ne souhaite garder que le nom des User, ce qui revient à
				// dire qu'on souhaite transformer un User en un String. C'est
				// l'étape de mapping.
				// map() prend une Function qui prend un T en paramètre et
				// renvoi un R en sorti, où T est le type du stream et R un type
				// quelconque, déterminé par le type du return de la Function.
				// NB : Lorsque une seule ligne suffit dans une lambda
				// expression, on peut se passer des {} et du return, comme suit
				.map(user -> user.getNom())
				// Transformation du Stream en List pour exploitation future.
				// collect() est une méthode dite terminale, car elle ne renvoi
				// pas de Stream.
				// C'est la méthode qui va déclencher le début du traitement,
				// tous les appels précédents n'ont rien fait jusqu'ici, c'est
				// la Lazy evaluation.
				// C'est pour cela qu'on parle de code déclaratif, car on ne
				// fait que décrire un résultat souhaité, et Java s'occupe des
				// détails d'exécution tout seul (et optimisés en plus, sans
				// qu'on ai besoin de s'en préoccuper)
				.collect(Collectors.toList());

		// Fin du timing de méthode
		Instant end = Instant.now();
		// Utiliastion de Duration, toujours extrait de Joda time qui représente
		// une durée entre deux Instant.
		// Il y a d'autres méthodes to*** avec d'autres unités.
		System.out.println(Duration.between(start, end).toMillis());
		// Pas de boucle for pour ajouter une "," entre tous les noms de la
		// liste, mais la nouvelle méthode join() de String qui met la chaine
		// passé en paramètre en tous les éléments de l'Iterable passé en second
		// paramètre.
		System.out.println(String.join(",", names));
	}

	
	public static void main(String[] args) {
		UserService us = new UserService();
		us.printNomsPeronnesMajeures(UserUtil.getHugeListOfUser());
	}

}
