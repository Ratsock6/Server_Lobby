package fr.ratsock.lobby.utils.head;

import org.bukkit.inventory.ItemStack;

public enum HeadList {

	PIG("ZGY0MmZjMTI1MWFmN2ZiZDYzZmI2MDEyODNmNzFkNTcyYWMxNjk0ZWJlMWE5NmYzNWU0MDkyMjI1YjJkZDMyMiJ9fX0=", "pig"),
	PROPOS("ZjExODU2MTVkNWNjN2M3MDBlYWJjYjdkYjA5N2VkNzIxZDU4OWZkZmVlZjlmMDMzMzM2YzI2Yzk4OGU0YmU0NiJ9fX0=", "propos"),
	AMI("ZjYyYzQ4NWIxODg2ZGJjZTZjMWNhZDE0MGMwZWY4NzYzNTU5ZDQzYTc4NTY0NDY2NGM2ZDVmMzZlMjc1NGVlOCJ9fX0=", "ami"),
	GROUPE("NjdiNGM2NTc0ZWEwMmE1ZjMwMjcyMzhmYmU0NTkwMjc2NDljMTc4ODNhZWMxMjk3OGJhYzg4NjkxNDkzMTgifX19", "groupe"),
	BOUTIQUE("ZjM3Y2FlNWM1MWViMTU1OGVhODI4ZjU4ZTBkZmY4ZTZiN2IwYjFhMTgzZDczN2VlY2Y3MTQ2NjE3NjEifX19", "boutique"),
	PARA("NDQyMzg2OGU1OGMyM2EyZDQ0YzE1NGQzYmFkMzA0NGRjODE1NzM1MTM2ZjNkMmRkZTJjZDRkOGE5OTE3In19fQ==", "para"),
	HOST("NGNlZDM0MjExZmVkNDAxMGE4Yzg1NzI0YTI3ZmE1ZmIyMDVkNjc2ODRiM2RhNTE3YjY4MjEyNzljNmI2NWQzZiJ9fX0=", "host"),
	SPAWN("Njg5MWY1YzIzYTU0MzY0MzI0NGJjNjEwMjA0ZTQ3NWFhZjczMWRkYTVjOTZiMjY4ZGM1ZDkyMmVhODMyN2E4YyJ9fX0=", "spawn"),
	COSMETIQUE("MTc4OWIzZTI4NjhkNzE2YTkyMWRlYzU5MzJkNTMwYTg5MmY2MDAyMzVmMTg3NzY2YmMwMmQxNDVlZDE2ODY1YiJ9fX0=", "cosmetique"),
	SOCIAL("YzVlMjc5ODhhNjUzODAxMGVmYzBlMjQ3NTZiYzNlM2VlYTI0ZDc1MzZiMjA5MzJjMTdlMDQwNGU1Y2M1NWYifX19", "social"),
	LANGUE("MmUyY2M0MjAxNWU2Njc4ZjhmZDQ5Y2NjMDFmYmY3ODdmMWJhMmMzMmJjZjU1OWEwMTUzMzJmYzVkYjUwIn19fQ==", "langue"),
	RRUMBLE("NjY1YzRiMzliZDZkODBkNGNlN2NkMmNiOTlmYmFkNThjODA3OTg3MmRkNGZmNmE4NDIzZDE1ZjQxMmFiYzkifX19", "rrumble");


	private final ItemStack item;

	private final String idTag;

	private final String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

	HeadList(String texture, String id) {

		item = CreateSkull.createSkull(prefix + texture, id);
		idTag = id;

	}

	public ItemStack getItemStack() {
		return item;
	}

	public String getName() {
		return idTag;
	}

}
