/* Generated By:JavaCC: Do not edit this line. Reader.java */
        package Reader;
        import java.util.TreeMap;
        import java.util.Map;
        import Circuit.*;
        import Composant.*;
        import Composite.*;
        import BaseComposite.*;
        public class Reader implements ReaderConstants {
                public _Circuit read(String s) throws Exception
                { return CIRCUIT();}
                public TreeMap<String,Composite> readComposite() throws Exception {
                        return LISTECOMPOSITE();}

  final public int Nombre() throws ParseException, Exception {
  Token t;
    t = jj_consume_token(NUM);
         {if (true) return  Integer.parseInt(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public String Nom() throws ParseException, Exception {
  Token t;
    t = jj_consume_token(ID);
         {if (true) return  t.image;}
    throw new Error("Missing return statement in function");
  }

  final public $Composant TYPE(String type) throws ParseException, Exception {
  $Composant composant = null;
                switch (type)
                {
                        case "Itr" : composant = ($Composant)new Itr();break;
                        case "Gnd" : composant = ($Composant)new Gnd();break;
                        case "Vcc" : composant = ($Composant)new Vcc();break;
                        case "Led" : composant = ($Composant)new Led();break;
                        case "Et" : composant = ($Composant)new Et();break;
                        case "Ou" : composant = ($Composant)new Ou();break;
                        case "Non" : composant = ($Composant)new Non();break;
                        case "Oux" : composant = ($Composant)new Oux();break;
                }
                {if (true) return composant;}
    throw new Error("Missing return statement in function");
  }

/**Fonction de création du circuit*/
  final public _Circuit CIRCUIT() throws ParseException, Exception {
        $Composant compo;
        Circuit circuit = new Circuit();
        int i;
        TreeMap <Integer,TreeMap<Integer,Integer>> sortie = new TreeMap <Integer,TreeMap<Integer,Integer>>();
        TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>> listeConnexion = new TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>>();
        String type = "";
    jj_consume_token(ID);
    jj_consume_token(C_OUVERT);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case P_OUVERT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      if (jj_2_1(10)) {
        compo = COMPOSANT(listeConnexion);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case P_OUVERT:
          compo = COMPOSITE(listeConnexion);
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
                         circuit.ajouter(compo,compo.getNum());
    }
    jj_consume_token(C_FERME);
                        //Lier les coposant entre eux
                        //Pour chaque composant
                        for(i = 1; i <listeConnexion.size(); i++)
                        {
                                //Obtenir liste des connexion sortie
                                sortie = listeConnexion.get(i);
                                //Pour chaque port de sortie
                                for(Map.Entry<Integer,TreeMap<Integer,Integer>> entry : sortie.entrySet())
                                {
                                        //entry.getKey() = numPort de sortie
                                        for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet())
                                        { //entry2.getKey() = compoENtre, entry2.getValues() = portEntre		  		
                                                circuit.connecter(i,entry.getKey(),entry2.getKey(),entry2.getValue());
                                        }
                                }
                        }
                        {if (true) return circuit;}
    throw new Error("Missing return statement in function");
  }

/**Fonction de création du composant*/
  final public $Composant COMPOSANT(TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>> co) throws ParseException, Exception {
        int nb_sorties;
        int nb_entrees;
        int port;
        int indice;
        String facultatif;
        String type;
        TreeMap <Integer,TreeMap<Integer,Integer>> sortie = new TreeMap <Integer,TreeMap<Integer,Integer>>();

        $Composant compo;
    jj_consume_token(P_OUVERT);
    indice = Nombre();
    jj_consume_token(SEPARATEUR);
    type = Nom();
    compo = TYPE(type);
         compo.ajouterNum(indice);
    jj_consume_token(P_OUVERT);
    nb_entrees = Nombre();
    jj_consume_token(SEPARATEUR);
    nb_sorties = Nombre();
    jj_consume_token(P_FERME);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case P_OUVERT:
      jj_consume_token(P_OUVERT);
      facultatif = Nom();
      jj_consume_token(P_FERME);
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FLECHE:
      jj_consume_token(FLECHE);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HASHTAG:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      ENTRE_SORTIE(sortie);
    }
    jj_consume_token(P_FERME);
         co.put(indice,sortie);{if (true) return compo;}
    throw new Error("Missing return statement in function");
  }

/**"<".NUM."|".TYPE."(".NUM.",".NUM.")"."[".SORTIE_ENTRE.COMPOSANT."]".[ID]."->".SORTIE_ENTRE.">"*/
/**Fonction de création du Composite*/
  final public $Composant COMPOSITE(TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>> co) throws ParseException, Exception {
        int nb_sorties;
        int nb_entrees;
        int indiceCompo = 0;
        int numPort = 0;
        int i;
        int indice;
        String facultatif;
        String type;
        Composite compo;
        $Composant c;

        TreeMap <Integer,TreeMap<Integer,Integer>> s = new TreeMap <Integer,TreeMap<Integer,Integer>>();
        TreeMap <Integer,TreeMap<Integer,Integer>> connexionSortie = new TreeMap <Integer,TreeMap<Integer,Integer>>();
        TreeMap <Integer,TreeMap<Integer,Integer>> connexionEntre = new TreeMap <Integer,TreeMap<Integer,Integer>>();
        TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>> listeConnexion = new TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>>();
    jj_consume_token(P_OUVERT);
    indice = Nombre();
    jj_consume_token(SEPARATEUR);
    type = Nom();
    jj_consume_token(P_OUVERT);
    nb_entrees = Nombre();
    jj_consume_token(SEPARATEUR);
    nb_sorties = Nombre();
    jj_consume_token(P_FERME);
                compo = new Composite(nb_entrees,nb_sorties,type);
                compo.ajouterNum(indice);
    jj_consume_token(C_OUVERT);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HASHTAG:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      ENTRE_SORTIE(connexionEntre);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEPARATEUR:
        jj_consume_token(SEPARATEUR);
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case P_OUVERT:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      if (jj_2_2(10)) {
        c = COMPOSANT(listeConnexion);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case P_OUVERT:
          c = COMPOSITE(listeConnexion);
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
         compo.ajouter(c,c.getNum());
    }
    jj_consume_token(C_FERME);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case P_OUVERT:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_5;
      }
      jj_consume_token(P_OUVERT);
      facultatif = Nom();
      jj_consume_token(P_FERME);
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FLECHE:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_6;
      }
      jj_consume_token(FLECHE);
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case HASHTAG:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_7;
      }
      ENTRE_SORTIE(connexionSortie);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEPARATEUR:
        jj_consume_token(SEPARATEUR);
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
    }
    jj_consume_token(P_FERME);
                //Connecter les port d'entrées
                for(i = 1; i < nb_entrees+1; i++)
                {
                        for(Map.Entry<Integer,Integer> entry : connexionEntre.get(i).entrySet())
                        {
                                compo.connecterEntre(i,entry.getKey(),entry.getValue());
                        }
                }
                //Lier les coposant entre eux
                //Pour chaque composant
                for(i = 1; i <listeConnexion.size()+1; i++)
                {
                        //Obtenir liste des connexion sortie
                        s = listeConnexion.get(i);

                        //Pour chaque port de sortie
                        for(Map.Entry<Integer,TreeMap<Integer,Integer>> entry : s.entrySet())
                        {
                                //entry.getKey() = numPort de sortie
                                for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet())
                                { //entry2.getKey() = compoENtre, entry2.getValues() = portEntre

                                  if(entry2.getKey() == -1)//Connexion sortie
                                  { compo.connecterSortie(i,entry.getKey(),entry2.getValue()); }
                                  //Connexion normale
                                  else {compo.connecter(i,entry.getKey(),entry2.getKey(),entry2.getValue()); }
                                }
                        }
                }
                co.put(compo.getNum(),connexionSortie);
                {if (true) return compo;}
    throw new Error("Missing return statement in function");
  }

/**Lire les sortie d'un composant ou d'un composite, Association #port(numCompo2#port2...),...*/
  final public void ENTRE_SORTIE(TreeMap <Integer,TreeMap<Integer,Integer>> listeCo) throws ParseException, Exception {
  int numCompo2;
  int port2;
  int port;
  TreeMap <Integer,Integer> liste = new TreeMap <Integer,Integer>();
    jj_consume_token(HASHTAG);
    port = Nombre();
    jj_consume_token(P_OUVERT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HASHTAG:
      label_8:
      while (true) {
        jj_consume_token(HASHTAG);
        port2 = Nombre();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPARATEUR:
          jj_consume_token(SEPARATEUR);
          break;
        default:
          jj_la1[13] = jj_gen;
          ;
        }
                 liste.put(-1,port2);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case HASHTAG:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_8;
        }
      }
      break;
    case NUM:
      label_9:
      while (true) {
        numCompo2 = Nombre();
        jj_consume_token(HASHTAG);
        port2 = Nombre();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPARATEUR:
          jj_consume_token(SEPARATEUR);
          break;
        default:
          jj_la1[15] = jj_gen;
          ;
        }
                 liste.put(numCompo2,port2);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NUM:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_9;
        }
      }
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(P_FERME);
         listeCo.put(port,liste);
  }

/**Lire une liste de composite*/
  final public TreeMap<String,Composite> LISTECOMPOSITE() throws ParseException, Exception {
        String nom;
        TreeMap<String,Composite> listeComposant = new TreeMap<String,Composite>();
        $Composant compo;
        TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>> co = new TreeMap <Integer,TreeMap<Integer,TreeMap<Integer,Integer>>>();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case P_OUVERT:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_10;
      }
      compo = COMPOSITE(co);
         listeComposant.put(compo.obtenirType(),((Composite) compo));
    }
         {if (true) return listeComposant;}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_3R_17() {
    if (jj_scan_token(HASHTAG)) return true;
    return false;
  }

  private boolean jj_3R_16() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_scan_token(P_OUVERT)) return true;
    if (jj_3R_12()) return true;
    if (jj_scan_token(SEPARATEUR)) return true;
    if (jj_3R_13()) return true;
    if (jj_3R_14()) return true;
    if (jj_scan_token(P_OUVERT)) return true;
    if (jj_3R_12()) return true;
    if (jj_scan_token(SEPARATEUR)) return true;
    if (jj_3R_12()) return true;
    if (jj_scan_token(P_FERME)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_15()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(13)) jj_scanpos = xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_16()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(P_FERME)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_scan_token(NUM)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_14() {
    return false;
  }

  private boolean jj_3R_15() {
    if (jj_scan_token(P_OUVERT)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public ReaderTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[19];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200,0x200,0x200,0x2000,0x8000,0x8000,0x4000,0x200,0x200,0x200,0x2000,0x8000,0x4000,0x4000,0x8000,0x4000,0x20,0x8020,0x200,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Reader(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Reader(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ReaderTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Reader(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ReaderTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Reader(ReaderTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ReaderTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 19; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[16];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 19; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 16; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 2; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

        }
