
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * We ensure that the neighboring file opens well
     */
    @Test
    public void shouldOpenPersonActivity() {
        // On clique sur le 1er de la liste de MyNeighbours
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));
        // On vérifie que la vue a bien été ouverte
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(isDisplayed()));
    }

    /**
     * We ensure that the neighbour displayed is the 1st on the list
     */
    @Test
    public void shouldDisplayNeighbourName() {
        // On clique sur le 1er de la liste de MyNeighbours
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));
        // On vérifie que son nom est bien Caroline
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(withText("Caroline")));
    }

    /**
     * We ensure that the neighbor is deleted from the list_favorites_neighbours
     */
    @Test
    public void checkFavorites() {
        // On vérifie que la liste des favoris est vide
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(withItemCount(0));

        // On clique sur le 1er voisin en position 0
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));

        // On vérifie que le nom correspond bien à sa position
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(withText("Caroline")));

        // On clique sur le bouton d'ajout aux favoris
        onView(withId(R.id.add_favorite_star))
                .perform(click());

        // On clique sur retour en arrière
        pressBack();

        // On reclique sur l'onglet favorites
        onView(withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());

        // On vérifie que la liste des favoris contient 1 voisin
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(withItemCount(1));

        // On clique sur le 1er élement de la liste voisin qui s'y trouve
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));

        // On clique sur l'étoile
        onView(withId(R.id.add_favorite_star))
                .perform(click());

        // On revient en arrière et vérifie que la liste est vide
        pressBack();

        onView(ViewMatchers.withId(R.id.list_favorite_neighbours)).check(withItemCount(0));
    }


}