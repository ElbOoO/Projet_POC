package fr.telecom.Poc.Utils;

public enum ListeRoles {
	User, Manager, Admin;

	public static Boolean isPresent(String str) {
		for (ListeRoles r : ListeRoles.values()) {
			if (r.name().equals(str)) {
				return true;
			}
		}
		return false;
	}
}
