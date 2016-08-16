package javaa.main.model.domain;

import javaa.main.model.dataAPI.model.DtoBudget;
import javaa.main.model.dataAPI.model.DtoItem;
import javaa.main.model.dataAPI.model.DtoItems;
import javaa.main.model.dataAPI.model.DtoSection;

import java.util.ArrayList;

/**
 * Created on 16/08/2016.
 */
public class ParserToDomain {


    public static Budget parseDtoBudget(DtoBudget dtoBudget) {
        Budget budget = new Budget();
        budget.setName(budget.getName());
        budget.setAddress(budget.getAddress());

        ArrayList<Items> i = new ArrayList<>();
        for (DtoItems dto : dtoBudget.getItems()) {
            if (dto instanceof DtoItem)
                i.add(parseDtoItem((DtoItem) dto));
            else if (dto instanceof DtoSection)
                i.add(parseDtoSection((DtoSection) dto));
        }
        budget.setItems(i);
        return budget;
    }

    private static Section parseDtoSection(DtoSection dtoSection) {
        Section section = new Section();
        section.setDescription(dtoSection.getDescription());
        section.setValue(dtoSection.getValue());

        for(DtoItem dtoItem: dtoSection.getItems()) {
            section.addItem(parseDtoItem(dtoItem));
        }
        return section;
    }

    public static Item parseDtoItem(DtoItem dto) {
        Item item = new Item();
        item.setDescription(dto.getDescription());
        item.setValue(dto.getValue());
        return item;
    }
}
