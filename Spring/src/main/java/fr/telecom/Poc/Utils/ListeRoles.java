package fr.telecom.Poc.Utils;

/**
 * Liste des rôles possibles pour les Personnes
 */
public enum ListeRoles {
	ROLE_User, ROLE_Manager, ROLE_Admin;

	public static Boolean isPresent(String str) {
		for (ListeRoles r : ListeRoles.values()) {
			if (r.name().equals(str)) {
				return true;
			}
		}
		return false;
	}
}
