package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "06 05 12 20 65", "Paris", "Sed cautela nimia in peiores haeserat plagas, ut narrabimus postea, aemulis consarcinantibus insidias graves. Provincias amnis mare Gnaeus Orontes abstractas Tigrane praetermeans. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.","www.facebook.fr/caroline"),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "06 25 36 45 69", "Lille", "Hac ex causa conlaticia stipe Valerius humatur ille Publicola et subsidiis amicorum mariti. Mentitum neque utrumque utrumque ego. Nec firmare nostri abscisa ad.","www.facebook.fr/jack"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "06 12 48 79 96", "Marseille", "Victus universis caro ferina est lactisque abundans copia qua sustentantur, et herbae multiplices. Ut an an ut incertum. Accusatoris impleri quaerebatur quaerebatur confestim.","www.facebook.fr/chloe"),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "06 22 44 88 99", "Montpellier", "Ego vero sic intellego, Patres conscripti, nos hoc tempore in provinciis ? Sum datam accusationis patiebatur meae tuum more moleste illa meum.","www.facebook.fr/vincent"),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "06 32 14 95 78", "Bordeaux", "Metuentes igitur idem latrones Lycaoniam magna parte campestrem cum se inpares nostris fore.Fingerent intromissi vilitate rumores in honoratorum adsistendo nuntiabant audirent parum.","www.facebook.fr/elodie"),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "06 56 45 56 87", "Lyon", "Adolescebat autem obstinatum propositum erga haec et similia multa scrutanda lenitate feminea.Salis anteponere dicitur anteponere suavissima amicitiarum veterrima expletum edendos equis.","www.facebook.fr/sylvain"),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "06 21 87 59 63", "Grenoble", "Tu autem, Fanni, quod mihi tantum tribui dicis quantum ego nec adgnosco nec postulo.Cupiebat suffragatione pileo et fatidicum ignavia absolvi convictus quaeritatum ante.","www.facebook.fr/laetitia"),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "06 78 45 96 85", "Rouen", "Et quoniam apud eos ut in capite mundi morborum acerbitates celsius dominantur, ad quos vel sedandos omnis professio.Intempestivum qui sed cunctorum feriens concitat in rati distarent securitas.","www.facebook.fr/dan"),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "06 34 16 75 94", "Le Havre", "Paphius quin etiam et Cornelius senatores, ambo venenorum artibus pravis se polluisse confessi. Urbis inanes coluntur orbos inanes vero flatus vero vero sine.","www.facebook.fr/joseph"),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "06 96 57 81 03", "Ajaccio", "Paphius quin etiam et Cornelius senatores, ambo venenorum artibus pravis.Deferebatur aut pondera idus liquido Arelate ludos hiemem qui annum.","www.facebook.fr/emma"),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "06 07 45 12 36", "Versailles", "In his tractibus navigerum nusquam visitur flumen sed in locis plurimis aquae suapte natura calentes.Ludibriis sit artium doctor ludibriis vocali artium exundant tinnitu exundant.","www.facebook.fr/patrick"),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "06 78 06 75 94", "Strasbourg", "Sed si ille hac tam eximia fortuna propter utilitatem rei publicae frui non properat, ut omnia illa conficiat ? Et eminebat periculorum obterens per scilicet multa crimina Hispania Constanti.","www.facebook.fr/ludovic")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
