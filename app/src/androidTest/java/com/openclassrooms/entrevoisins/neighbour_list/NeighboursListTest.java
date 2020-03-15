
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ScrollToAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.ClickViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.security.auth.login.LoginException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
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
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighbourListClickAction () {
        // On clique sur le 1er de la liste de MyNeighbours
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));
        // On vérifie que son nom est bien Caroline
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(withText("Caroline")));

        // On vérifie la couleur de l'étoile * état pas ajouté aux favoris
       // TODO  onView(ViewMatchers.withId(R.id.add_favorite_star))
               /*.check(matches(withDrawable(R.drawable.ic_star_black_24dp)));*/


        onView(ViewMatchers.withId(R.id.add_favorite_star))
                .perform(new ClickViewAction ());
        pressBack();
        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(matches(hasChildCount(1)));
    }

    @Test
    public void myNeighbourListClickDelete () {

        // On clique sur le 1er voisin en position 0
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));

        // On vérifie que le nom correspond bien à sa position
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(withText("Caroline")));

        // On clique sur le bouton d'ajout aux favoris
        onView(withId(R.id.add_favorite_star)
                .perform(ScrollToAction().click());

        // On vérifie la couleur de l'étoile est bien jaune car ajouté aux favoris
        onView(withId(R.id.add_favorite_star))
                .check(matches(ImageView(R.drawable.ic_star_yellow_24dp)));

        // On clique sur retour en arrière
        pressBack();

        // On reclique sur l'onglet favorites
        onView(ViewMatchers.withId(R.id.tabItem2))
                .perform(new ClickViewAction ());

        // On clique sur le voisin qui s'y trouve
        onView(withId(R.id.list_favorite_neighbours))
            .perform(new ClickViewAction ());

        // On clique sur l'étoile
        onView(withId(R.id.add_favorite_star)
                .perform(click());

        // On vérifie que son état a changé - couleur noire
        onView(withId(R.id.add_favorite_star))
                .check(matches(ImageView(R.drawable.ic_star_border_black_24dp));

        // On revient en arrière et vérifie que la liste est vide
        pressBack();


/*

        // On clique sur le 1er voisin
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new ClickViewAction()));
        // On vérifie son prénom
        onView(ViewMatchers.withId(R.id.name_profil))
                .check(matches(withText("Caroline")));
       //On clique sur le bouton d'ajout aux favoris
        onView(matches(with(R.id.add_favorite_star)))
                .perform(new ClickViewAction ());

        onView(ViewMatchers.withId(R.id.list_favorite_neighbours))
                .check(matches(hasChildCount(0)));*/





        }








    }