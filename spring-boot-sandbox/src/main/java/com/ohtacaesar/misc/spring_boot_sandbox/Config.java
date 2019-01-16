package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Config {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private TagRepository tagRepository;

  @PostConstruct
  public void init() {
    List<Tag> tagList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Tag tag = new Tag();
      tag.setName("タグ" + i);
      tagList.add(tag);
    }
    tagRepository.save(tagList);

    List<Item> itemList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Item item = new Item();
      item.setName("アイテム" + i);
      itemList.add(item);

      ItemTag itemTag = new ItemTag();
      itemTag.setItem(item);
      itemTag.setTag(tagList.get(i));
      item.getItemTagList().add(itemTag);
    }
    itemRepository.save(itemList);
  }
}
