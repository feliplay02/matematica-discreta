import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

//Autors: Felip Torres Reines i Jaume Cantallops Comas

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1:Felip Torres Reines
 * - Nom 2:Jaume Catallops Comas
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els predicats de dues
   * variables són de tipus `BiPredicate<Integer, Integer>` i similarment s'avaluen com
   * `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per utilitzar la força bruta)
   */
  static class Tema1 {
    /*
     * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
     */
    static boolean exercici1(int[] universe,BiPredicate<Integer, Integer> p,Predicate<Integer> q,Predicate<Integer> r) {
        boolean cierto =true; 
        for (int x : universe) {
            for (int y : universe) {
                 if (p.test(x,y))
                    if (!((q.test(x) && r.test(y)))) {
                    cierto = false;
                }
            }
        }
        return cierto;
    }

    /*
     * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
       boolean xEncontrada = false;
        boolean cierto = false;
        for (int x : universe) {
            if((p.test(x))){
                cierto = true;
                if (xEncontrada){
                    cierto = false;
                }
               xEncontrada = true; 
            }else{
                boolean xCuenta = true;
                for (int y : universe) {
                    if (q.test(y)){
                        xCuenta=false;
                    }   
                }
                if (xCuenta){
                    cierto = true;
                    if (xEncontrada){
                        cierto = false;
                    }
                xEncontrada = true; 
                }
                 
            }
            
        }
        return cierto;
    }

    /*
     * És cert que ¬(∃x. ∀y. y ⊆ x) ?
     *
     * Observau que els membres de l'univers són arrays, tractau-los com conjunts i podeu suposar
     * que cada un d'ells està ordenat de menor a major.
     */
    static boolean exercici3(int[][] universe) {
        for (int i = 0; i < universe.length-1  ; i++) {
            //Creamos un array para almacenar los elementos de un array de la lista,
            //otro para almacenar los del siguiente a este y un ultimo para almacenar
            //booleanos que indicaran si el elemento en la posicion j del arrayY 
            //ha sido encontrado en el arrayX
            int[] arrayX=new int[universe[i].length];
            int[] arrayY=new int[universe[i+1].length];
            boolean[] encontrado= new boolean[arrayY.length];
            //almacenamos los elementos en los arrays correspondientes
            for (int j = 0; j < universe[i].length ; j++) {
                arrayX[j]= universe[i][j];
            }
            for (int j = 0; j < universe[i+1].length ; j++) {
                arrayY[j]= universe[i+1][j];
            }
            //comprueba si el elemento de arrayY esta en el arrayX
            for (int j = 0;j < arrayX.length;j++){
                for (int n = 0; n < arrayY.length;n++){
                    if(arrayY[n]==arrayX[j]){
                        encontrado[n]=true;
                    }
                        
                }
                
            }
            //analiza si no ha encontrado algun elemento y si no lo encuentra significara
            //que ∃y tal que ¬(y ⊆ x) por lo tanto es cierto.
            for (int j = 0; j < encontrado.length;j++){
                if(!encontrado[j]){
                    return true;
                }
            } 
        }
        return false;
    }

    /*
     * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
     */
    static boolean exercici4(int[] universe, int n) {
      return false; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // ∀x,y. P(x,y) -> Q(x) ^ R(y)

      assertThat(
          exercici1(new int[] { 2, 3, 5, 6 },(x, y) -> x * y <= 4,x -> x <= 3, x -> x <= 3)
      );

      assertThat(
          !exercici1(
              new int[] { -2, -1, 0, 1, 2, 3 },
              (x, y) -> x * y >= 0,
              x -> x >= 0,
              x -> x >= 0
          )
      );

      // Exercici 2
      // ∃!x. ∀y. Q(y) -> P(x) ?

      assertThat(
          exercici2(
              new int[] { -1, 1, 2, 3, 4 },
              x -> x < 0,
              x -> true
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6 },
              x -> x % 2 == 0, // x és múltiple de 2
              x -> x % 4 == 0  // x és múltiple de 4
          )
      );

      // Exercici 3
      // ¬(∃x. ∀y. y ⊆ x) ?

      assertThat(
          exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {} })
      );

      assertThat(
          !exercici3(new int[][] { {1, 2}, {0, 3}, {1, 2, 3}, {}, {0, 1, 2, 3} })
      );

      // Exercici 4
      // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

      assertThat(
          exercici4(
              new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
              11
          )
      );

      assertThat(
          !exercici4(
              new int[] { 0, 5, 7 },
              13
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com arrays (sense
   * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant int[] a,
   * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar com f.apply(x) (on
   * x és un enter d'a i el resultat f.apply(x) és un enter de b).
   */
  static class Tema2 {
    /*
     * És `p` una partició d'`a`?
     *
     * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`. Podeu suposar que
     * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
     */
    static boolean exercici1(int[] a, int[][] p) {
        //Array donde almacenaremos true si ese valor se ha encontrado en un conjunto
        boolean[] encontrado=new boolean[a.length];
        for (int i = 0;i<p.length;i++){
            for (int j = 0;j<p[i].length;j++){
                for (int n= 0;n<a.length;n++) {
                    if(p[i][j]==a[n]&&encontrado[n]==false){
                        encontrado[n]=true;
                    }else if(p[i][j]==a[n]&&encontrado[n]==true){
                        return false;
                    }
                }
            }
        }
        for(int i=0; i<a.length;i++){
            if(!encontrado[i]){
                return false;
            }
        }
      return true;
    }
    }

    /*
     * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que `x` n'és el mínim.
     *
     * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
     */
    static boolean exercici2(int[] a, int[][] rel, int x) {
        boolean[] encontrados=new boolean[a.length];
        //compruba si es reflexiva si no lo es devuelve falso        
        for (int i=0;i<a.length;i++){
            for (int j=0;j<rel.length;j++){
                if (rel[j][0]==a[i]&&rel[j][1]==a[i]){
                    encontrados[i]=true;
                }
            }
        }
        for (int i=0;i<encontrados.length;i++){
            if(!encontrados[i]){
                return false;
            }
        }
        //compruba si es antisimetrica
        for (int i=0;i<rel.length;i++){
            int[] relacion=new int[2];
            relacion[0]=rel[i][0];
            relacion[1]=rel[i][1];
            for (int j=0;j<rel.length;j++){
                if(rel[j][1]==relacion[0]&&rel[j][0]==relacion[1]&&rel[j][1]!=rel[j][0]){
                    return false;
                }
            }
        }
        //comprueba si es transitiva
        for (int i=0;i<rel.length;i++){
            int[] relacion=new int[2];
            relacion[0]=rel[i][0];
            relacion[1]=rel[i][1];
            for (int j=0;j<rel.length;j++){
                if(rel[j][0]==relacion[1]){
                    boolean encontrado;
                    int relacion2=rel[j][1]; 
                    for (int n=0;n<rel.length;n++){
                        if (rel[n][0]==relacion[0]&&rel[n][1]==relacion2){
                            encontrado=true;
                            if (!encontrado)
                                return false;
                        }
                    }
                    
                }
                
            }
        }
        
      return true; 
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Trobau l'antiimatge de
     * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`). Podeu suposar
     * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats de menor a major.
     */
    static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {
      int[] antiImagen=new int[dom.length];
        for(int i = 0;i<dom.length;i++){
            int n=0;
            if(f.apply(i)==y){
                antiImagen[n]=i;
                n++;
            }
        }
        for(int i = 0;i<antiImagen.length;i++){
            System.out.println(antiImagen[i]);
        }    
        return antiImagen;
    }

    /*
     * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
     * - 3 si `f` és bijectiva
     * - 2 si `f` només és exhaustiva
     * - 1 si `f` només és injectiva
     * - 0 en qualsevol altre cas
     *
     * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per comoditat, podeu
     * utilitzar les constants definides a continuació:
     */
    static final int NOTHING_SPECIAL = 0;
    static final int INJECTIVE = 1;
    static final int SURJECTIVE = 2;
    static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

    static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
        boolean injectiva = true;
        boolean exhaustiva = true;
        boolean bijectiva = true;
        
        boolean[] encontrado=new boolean[codom.length];
        for(int i=0;i<codom.length;i++){
            for(int j=0;j<codom.length;j++){
                if(f.apply(i)==codom[j]&&encontrado[j]==false){
                    encontrado[j]=true;
                }else if(f.apply(i)==codom[j]&&encontrado[j]==true){
                    injectiva=false;
                    bijectiva=false;
                }
            }
        }
        for(int i=0;i<encontrado.length;i++){
            if(!encontrado[i]){
                exhaustiva=false;
                bijectiva=false;  
            }
        }
        if(bijectiva){
            return 3;
        }else if(exhaustiva){
            return 2;
        }else if(injectiva){
            return 1;
        }else{
            return 0;
        }
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `p` és una partició d'`a`?

      assertThat(
          exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {3, 5}, {4} }
          )
      );

      assertThat(
          !exercici1(
              new int[] { 1, 2, 3, 4, 5 },
              new int[][] { {1, 2}, {5}, {1, 4} }
          )
      );

      // Exercici 2
      // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

      ArrayList<int[]> divisibility = new ArrayList<int[]>();

      for (int i = 1; i < 8; i++) {
        for (int j = 1; j <= i; j++) {
          if (i % j == 0) {
            // i és múltiple de j, és a dir, j|i
            divisibility.add(new int[] { j, i });
          }
        }
      }

      assertThat(
          exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3 },
              new int[][] { {1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3} },
              1
          )
      );

      assertThat(
          !exercici2(
              new int[] { 1, 2, 3, 4, 5, 6, 7 },
              divisibility.toArray(new int[][] {}),
              2
          )
      );

      // Exercici 3
      // calcular l'antiimatge de `y`

      assertThat(
          Arrays.equals(
              new int[] { 0, 2 },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1 },
                  x -> x % 2, // residu de dividir entre 2
                  0
              )
          )
      );

      assertThat(
          Arrays.equals(
              new int[] { },
              exercici3(
                  new int[] { 0, 1, 2, 3 },
                  new int[] { 0, 1, 2, 3, 4 },
                  x -> x + 1,
                  0
              )
          )
      );

      // Exercici 4
      // classificar la funció en res/injectiva/exhaustiva/bijectiva

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> (x + 1) % 4
          )
          == BIJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3, 4 },
              x -> x + 1
          )
          == INJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1 },
              x -> x / 2
          )
          == SURJECTIVE
      );

      assertThat(
          exercici4(
              new int[] { 0, 1, 2, 3 },
              new int[] { 0, 1, 2, 3 },
              x -> x <= 1 ? x+1 : x-1
          )
          == NOTHING_SPECIAL
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Aritmètica).
   *
   */
  static class Tema3 {
    /*
     * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
     *
     * Podeu suposar que `a` i `b` són positius.
     */
    static int exercici1(int a, int b) {
      
        int a1 = a;
        int b1 = b;
        //comprovar si a es menor a b. Si es así, cambiarlos para que el 
        //algorismo se puede ejecutar
        if (a1 < b1){
            b1 = a1;
            a1 = b1;
        }
        //declaramos variables para guardar los resultados de las operaciones
        //que haremos
        int d = 0;
        int r = 0;
        int r1 = 0;
        
        //calcular el primer resto para saber si hay que entrar en el bucle
        r = a % b;
        //guardamos lo que han pasado del parametro b a de para que la primera
        //interacción del bucle se ejecute adecuadamete
        d = b;
        //si se da el caso que  el primer resto (r) es cero será porqué 
        //el mcd es igual al número más pequeño introducido por parametro
        r1 = b;
        while(r != 0){
            r1 = r;
            r = d%r;
            d = r1;
     
        }  
        
      return r1; 
      
    }

    /*
     * Es cert que `a``x` + `b``y` = `c` té solució?.
     *
     * Podeu suposar que `a`, `b` i `c` són positius.
     */
    static boolean exercici2(int a, int b, int c) {
      
        //la equación tendra solución si el mcd(a,b) = d divide a c o lo que es lo
        //mismo, si d|c o también, c modulo d = 0 
        
        //aprovechamos el ejecicio uno para calcular el mcd de a y b
        int d = exercici1(a,b);

        //no me va bien hacerlo con resto así que calculo el resto
        int cocienteDe_c_d;
        int restoDe_c_d;
        
        cocienteDe_c_d = c/d;

        restoDe_c_d = c-(d*cocienteDe_c_d);
        
        //condición de resultado (lo que se haria con un if else)
        return restoDe_c_d == 0;
    }

    /*
     * Quin es l'invers de `a` mòdul `n`?
     *
     * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
     */
    static int exercici3(int a, int n) {
      
        //guardamos el mcd de a y n en la variable mcd
        int mcd = exercici1(a, n);
        
        //[a] que pertence a "Zn" es invertible si cumple mcd(a,n) = 1, si no lo 
        //cumple afirmar que no tendrá inversos
        if (mcd == 1){
            
            boolean encontrado = false;
            
            for (int i = 1; i<n && (!encontrado);i++){
                
                if (((a*i)%n) == 1){
                    
                    encontrado = true;
                    return i;
                }
                
            }
            
        }
            
        return -1;
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `mcd(a,b)`

      assertThat(
              exercici1(2, 4) == 2
      );

      assertThat(
              exercici1(1236, 984) == 12
      );

      // Exercici 2
      // `a``x` + `b``y` = `c` té solució?

      assertThat(
              exercici2(4,2,2)
      );
      assertThat(
              !exercici2(6,2,1)
      );
      // Exercici 3
      // invers de `a` mòdul `n`
      assertThat(exercici3(2, 5) == 3);
      assertThat(exercici3(2, 6) == -1);
    }
  }

  static class Tema4 {
    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
     */
    static int[] exercici1(int[][] A) {
        //crear un array para la solucion
        //en la posición 0 del array se guardarla el orden y en la 1 se guardara
        //la mida del grafo
        int [] grafo = new int [2];

        //crear un array de booleaos para no contar la misma aresta
        //por defecto todas las componentes se inicializan a false
        boolean [][] aristas = new boolean [A.length][A[0].length];

       //crear un contador de las aristas
       int numAristas = 0;


        for (int i = 0;i<A.length;i++){

            for (int j = 0;j<A[0].length;j++){

                if(aristas[i][j] == false && A[i][j] == 1){
                    numAristas++;
                    aristas[i][j] = true;
                    aristas[j][i] = true;
                }

            }

        }
        

        //orden del grafo
        grafo[0] = A.length;
  
        //mida del grafo
        grafo[1] = numAristas;
       
        return grafo;
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
     */
    static boolean exercici2(int[][] A) {
        Boolean euleria=true;
      
        //crear un contador de las aristas
        int numAristas = 0;


        for (int i = 0;i<A.length;i++){
            for (int j = 0;j<A[0].length;j++){
                if( A[i][j] == 1){
                    numAristas++;
                }

            }
            if (numAristas%2!=0){//compruba si el grado es impar
                euleria = false;
            }
            numAristas=0;
        }
        return euleria; 
    }

    /*
     * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors i de l'arrel,
     * retornau el nombre total de vèrtexos de l'arbre
     *
     */
    static int exercici3(int n, int d) {
        //variable que representa los nodos totales
        int nt= 0;
        //variable que representa los nodos por encima de las ojas (cada oja
        //tiene una arista hacia el nodo anterior)
        int nPorEncima = n;
        
        
        while (true){
        
            nt += nPorEncima - nPorEncima%d;
            nPorEncima = nPorEncima/d + nPorEncima%d;
            
            if (nPorEncima == 1) {
                
                //sumamos uno porque tenemos que contar el nodo de la raiz
                return nt + 1;
            }
        }
    }

    /*
     * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
     */
    static boolean exercici4(int[][] A) {
      return false; // TO DO
    }
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // `ordre i mida`

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}}), new int[] {3, 2})
      );

      assertThat(
              Arrays.equals(exercici1(new int[][] { {0, 1, 0, 1}, {1, 0, 1, 1}, {0 , 1, 0, 1}, {1, 1, 1, 0}}), new int[] {4, 5})
      );

      // Exercici 2
      // `Es eulerià?`

      assertThat(
              exercici2(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici2(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}})
      );
      // Exercici 3
      // `Quants de nodes té l'arbre?`
      assertThat(exercici3(5, 2) == 9);
      assertThat(exercici3(7, 3) == 10);

      // Exercici 4
      // `Conté algún cicle?`
      assertThat(
              exercici4(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
      );
      assertThat(
              !exercici4(new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
      );

    }
  }


  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
