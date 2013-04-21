package com.alexaitken.gildedrose;

public class Inventory {

	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	private Item[] items;

	public Inventory(Item[] items) {
		super();
		this.items = items;
	}

	public Inventory() {
		super();
		items = new Item[] { new Item("+5 Dexterity Vest", 10, 20),
				new Item(AGED_BRIE, 2, 0),
				new Item("Elixir of the Mongoose", 5, 7),
				new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80),
				new Item(BACKSTAGE_PASSES, 15, 20),
				new Item("Conjured Mana Cake", 3, 6) };

	}

	public void updateQuality() {

		for (int i = 0; i < items.length; i++) {
			Item item = items[i];

			if (item.getName().equals("Conjured")) {
				updateItem(item, 2);
			}
			else if (item.getName().equals(AGED_BRIE)) {
				updateItemAgedBrie(item);
			}
			else if (item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
				updateItemSulfuras(item);
			}
			else if (item.getName().equals(BACKSTAGE_PASSES)) {

				items[i].setQuality(items[i].getQuality() + 1);

				if (items[i].getSellIn() < 11) {
					items[i].setQuality(items[i].getQuality() + 1);
				}

				if (items[i].getSellIn() < 6) {
					items[i].setQuality(items[i].getQuality() + 1);

				}
				if (items[i].getQuality() > 50) {
					items[i].setQuality(50);
				}

				items[i].setSellIn(items[i].getSellIn() - 1);

				if (items[i].getSellIn() < 0) {
					items[i].setQuality(0);
				}
			}
			else {
				updateItem(item, 1);
			}

		}
	}

	private void updateItem(Item item, int decreaseFactor) {
		item.setSellIn(item.getSellIn() - 1);

		if (item.getSellIn() < 0) {
			decreaseFactor = decreaseFactor * 2;
		}
		item.setQuality(item.getQuality() - decreaseFactor);
		if (item.getQuality() < 0) {
			item.setQuality(0);
		}
	}

	private void updateItemAgedBrie(Item item) {
		item.setSellIn(item.getSellIn() - 1);
		if (item.getQuality() < 50) {
			item.setQuality(item.getQuality() + 1);
		}
	}

	private void updateItemSulfuras(Item item) {
	}
}
