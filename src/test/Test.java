package test;

import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import metier.CategorieCrud;
import metier.ProduitCrud;

public class Test {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProduitCrud produitCrud = new ProduitCrud();
    private static final CategorieCrud categorieCrud = new CategorieCrud();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";
 
    public static void main(String[] args) throws IOException {
    	int code,qte;
    	float prix;
    	int id_category;
    	String designation;
    	authenticateAdmin(); // New line to check admin authentication

    	while (true) {
            int choixGlobal = MenuPrincipal();
            if (choixGlobal == 3) {
                System.out.println("Vous avez choisi de quitter. Au revoir !");
                break;
            } else if (choixGlobal == 1) {
                traiterProduit();
            } else if (choixGlobal == 2) {
                traiterCategorie();
            } else {
                System.out.println("Veuillez entrer un nombre valide !");
            }
        }
    }
    private static void authenticateAdmin() {
        System.out.print("Entrez le nom d'utilisateur : ");
        String username = scanner.nextLine();

        System.out.print("Entrez le mot de passe  : ");
        String password = scanner.nextLine();

        if (!ADMIN_USERNAME.equals(username) || !ADMIN_PASSWORD.equals(password)) {
            System.out.println("Authentification échouée. Quitter le programme.");
            System.exit(0);
        }
    }

    private static void traiterProduit() throws IOException {
        while (true) {
            int choixProduit = afficherMenuPrincipalProduit();
            if (choixProduit == 7) {
                break;
            }
            traiterOptionProduit(choixProduit);
        }
    }

    private static void traiterCategorie() throws IOException {
        while (true) {
            int choixCategorie = afficherMenuPrincipalCategorie();
            if (choixCategorie == 6) {
                break;
            }
            traiterOptionCategorie(choixCategorie);
        }
    }

    private static int MenuPrincipal() {
        System.out.println("Choisissez une option :");
        System.out.println("1. Produit");
        System.out.println("2. Catégorie");
        System.out.println("3. Quitter");
        System.out.println("Entrez votre choix :");

        return getChoiceInput(1, 3);
    }

    private static int afficherMenuPrincipalProduit() {
        System.out.println("Choisissez votre option :");
        System.out.println("1. Afficher les produits");
        System.out.println("2. Ajouter un produit");
        System.out.println("3. Mettre à jour un produit");
        System.out.println("4. Supprimer un produit");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Rechercher par Catégorie");
        System.out.println("7. Quitter");
        System.out.println("Entrez votre choix");

        return getChoiceInput(1, 7);
    }

    private static int afficherMenuPrincipalCategorie() {
        System.out.println("Choisissez votre option :");
        System.out.println("1. Afficher les catégories");
        System.out.println("2. Ajouter une catégorie");
        System.out.println("3. Mettre à jour une catégorie");
        System.out.println("4. Supprimer une catégorie");
        System.out.println("5. Rechercher une catégorie");
        System.out.println("6. Quitter");
        System.out.println("Entrez votre choix :");

        return getChoiceInput(1, 6);
    }

    private static int getChoiceInput(int min, int max) {
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice < min || choice > max) {
            System.out.println("Veuillez entrer un nombre valide !");
            return getChoiceInput(min, max);
        }
        return choice;
    }

    private static void traiterOptionProduit(int choix) throws IOException {
        switch (choix) {
            case 1:
                produitCrud.afficherAllProduit();
                break;
            case 2:
                ajouterProduit();
                break;
            case 3:
                mettreAJourProduit();
                break;
            case 4:
                supprimerProduit();
                break;
            case 5:
                rechercherProduit();
                break;
            case 6:
                rechercherParCategorie();
                break;
            default:
                break;
        }
    }

    private static void ajouterProduit() {
    	System.out.println("\t\t\tAjouter un produit:");
        System.out.print("Donnez le code du produit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        int code = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Donnez la désignation du produit: ");
        String designation = scanner.nextLine();
        System.out.print("Donnez le prix du produit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        Float prix = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Donnez la quantité du produit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        int qte = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Donnez la catégorie du produit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
        int id_category = scanner.nextInt();
        produitCrud.ajouterProduit(code, designation, prix, qte,id_category);
      }


   private static void mettreAJourProduit() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Vous avez choisi Mettre à jour un produit");
        System.out.print("Entrez le code du produit à mettre à jour : ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            scanner.next();
        }
       int  code = scanner.nextInt();
        scanner.nextLine();
        boolean p = produitCrud.existProduit(code);
      if(p==true) {
   	   System.out.print("Donnez la désignation du produit: ");
           String designation = scanner.nextLine();

          System.out.print("Donnez le prix du produit: ");
          while (!scanner.hasNextInt()) {
              System.out.println("Veuillez entrer un nombre.");
              scanner.next();
          }
           Float prix = scanner.nextFloat();
          scanner.nextLine();

          System.out.print("Donnez la quantité du produit: ");
          while (!scanner.hasNextInt()) {
              System.out.println("Veuillez entrer un nombre.");
              scanner.next();
          }
          int qte = scanner.nextInt();
           produitCrud.updateProduit(code, designation, prix, qte);
      }
    }

    private static void supprimerProduit() throws JsonParseException, JsonMappingException, IOException {
        
        System.out.println("\nVous avez choisi Supprimer un produit");

        System.out.print("Entrez le code du produit à supprimer : ");
        int codeASupprimer = getValidInput("Veuillez entrer un nombre.");

        boolean productExists = produitCrud.existProduit(codeASupprimer);
        if (productExists) {
        	produitCrud.supprimerProduit(codeASupprimer);
            System.out.println("Le produit a été supprimé avec succès.");
        } else {
            System.out.println("\t\t\t\t Le produit n'existe pas.");
        }
       
    }

    private static void rechercherProduit() throws JsonParseException, JsonMappingException, IOException {
    
        System.out.println("Vous avez choisi Rechercher un produit");
        System.out.print("Entrez le code ou le prix ou la designation du produit à rechercher : ");
        if (scanner.hasNextInt()) {
        	//System.out.print(scanner.nextInt());
            int codeARechercher = scanner.nextInt();
            produitCrud.chercherProduit(String.valueOf(codeARechercher));
        } else {
            String mc = scanner.next();
            produitCrud.chercherProduit(mc);
        }

    }

    private static void rechercherParCategorie() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("\nVous avez choisi Lister par catégorie");

        // Assuming you have a method to get valid input
        String categoryDes = getValidInputs("Entrez la designation de la catégorie : ");
        
        // Call the method to search for the category
        categorieCrud.chercherCategorie(categoryDes);
    }

    // You can add a utility method to get valid input
    private static String getValidInputs(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());

        return input;
    }


//The getValidInput() method handles input validation for integers.
//The listerParCategory() method is called with the provided category ID.
// des mothode que j'ai utilisé dans les methodes prcedante
    private static int getValidInput(String errorMessage) {
        while (!scanner.hasNextInt()) {
            System.out.println(errorMessage);
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private static float getValidFloatInput(String errorMessage) {
        while (!scanner.hasNextFloat()) {
            System.out.println(errorMessage);
            scanner.next();
        }
        return scanner.nextFloat();
    }


    

    private static void traiterOptionCategorie(int choix) throws IOException {
        switch (choix) {
            case 1:
                categorieCrud.afficherAllCategorie();
                break;
            case 2:
                ajouterCategorie();
                break;
            case 3:
                mettreAJourCategorie();
                break;
            case 4:
                supprimerCategorie();
                break;
            case 5:
                rechercherCategorie();
                break;
            default:
                break;
        }
    }

   private static void ajouterCategorie() {
        System.out.println("\n\t\t\tAjouter une catégorie:");

        System.out.print("Donnez le code de la catégorie : ");
        int code = getValidInput("Veuillez entrer un nombre.");

        System.out.print("Donnez la désignation de la catégorie : ");
        String designation = scanner.next();

        categorieCrud.ajouterCategorie(code, designation);
    }

    private static void mettreAJourCategorie() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("\nVous avez choisi Mettre à jour une catégorie");

        System.out.print("Entrez le code de la catégorie à mettre à jour : ");
        int code = getValidInput("Veuillez entrer un nombre.");

        boolean categoryExists = categorieCrud.existCategorie(code);
        if (categoryExists) {
            System.out.print("Nouvelle désignation de la catégorie : ");
            String newDesignation = scanner.next();

            categorieCrud.updateCategorie(code, newDesignation);
            System.out.println("La catégorie a été mise à jour avec succès.");
        } else {
            System.out.println("\t\t\t\t La catégorie n'existe pas.");
        }
    }

    private static void supprimerCategorie() throws JsonParseException, JsonMappingException, IOException {
    System.out.println("\nVous avez choisi Supprimer une catégorie");

    System.out.print("Entrez le code de la catégorie à supprimer : ");
    int codeASupprimer = getValidInput("Veuillez entrer un nombre.");

    boolean categoryExists = categorieCrud.existCategorie(codeASupprimer);
    if (categoryExists) {
    	categorieCrud.supprimerCategorie(codeASupprimer);
        System.out.println("La catégorie a été supprimée avec succès.");
    } else {
        System.out.println("\t\t\t\t La catégorie n'existe pas.");
    }
    }

    private static void rechercherCategorie() throws JsonParseException, JsonMappingException, IOException {
    System.out.println("\nVous avez choisi Rechercher une catégorie");

    System.out.print("Entrez le code ou la désignation de la catégorie à rechercher : ");
    if (scanner.hasNextInt()) {
        int codeARechercher = scanner.nextInt();
        categorieCrud.chercherCategorie(String.valueOf(codeARechercher));
    } else {
        String designationARechercher = scanner.next();
        categorieCrud.chercherCategorie(designationARechercher);
    }
    }
}
