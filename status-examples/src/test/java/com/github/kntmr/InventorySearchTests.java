package com.github.kntmr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.kntmr.Inventory.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class InventorySearchTests {

    @Test
    public void すべてSTOCKEDの場合はSTOCKEDが返る() {
        // setup
        List<Inventory> targets = new ArrayList<>(Arrays.asList(STOCKED, STOCKED, STOCKED, STOCKED, STOCKED));

        // exercise
        InventorySearch sut = new InventorySearch();
        Inventory actual = sut.execute(targets);

        // verify
        assertThat(actual, is(STOCKED));
    }

    @Test
    public void すべてNO_STOCKEDの場合はNO_STOCKEDが返る() {
        // setup
        List<Inventory> targets = new ArrayList<>(Arrays.asList(NO_STOCKED, NO_STOCKED, NO_STOCKED, NO_STOCKED, NO_STOCKED));

        // exercise
        InventorySearch sut = new InventorySearch();
        Inventory actual = sut.execute(targets);

        // verify
        assertThat(actual, is(NO_STOCKED));
    }

    @Test
    public void すべてNO_HANDLINGの場合はNO_HANDLINGが返る() {
        // setup
        List<Inventory> targets = new ArrayList<>(Arrays.asList(NO_HANDLING, NO_HANDLING, NO_HANDLING, NO_HANDLING, NO_HANDLING));

        // exercise
        InventorySearch sut = new InventorySearch();
        Inventory actual = sut.execute(targets);

        // verify
        assertThat(actual, is(NO_HANDLING));
    }

    @Test
    public void すべてFEWNESSの場合はFEWNESSが返る() {
        // setup
        List<Inventory> targets = new ArrayList<>(Arrays.asList(FEWNESS, FEWNESS, FEWNESS, FEWNESS, FEWNESS));

        // exercise
        InventorySearch sut = new InventorySearch();
        Inventory actual = sut.execute(targets);

        // verify
        assertThat(actual, is(FEWNESS));
    }

    @Test
    public void すべてOTHERの場合はOTHERが返る() {
        // setup
        List<Inventory> targets = new ArrayList<>(Arrays.asList(OTHER, OTHER, OTHER, OTHER, OTHER));

        // exercise
        InventorySearch sut = new InventorySearch();
        Inventory actual = sut.execute(targets);

        // verify
        assertThat(actual, is(OTHER));
    }

}
