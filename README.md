BSDViewPagerIndicator
==========================

Indicatore view pager per android

Esempio di utilizzo nel progetto CNA Bologna.

Utilizzo
-----

Includere `BSDIndicator` nel layout XML insieme al `ViewPager`.

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v4.view.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <it.bsdsoftware.library.BSDIndicator
        android:id="@+id/indicator"
        android:padding="10dip"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:layout_alignParentRight="true"
        app:indicatorClickable="true"
        app:backgroundColorDeselected="@color/black"
        app:backgroundColorSelected="@color/green"
        app:textColorSelected="@color/blue"
        app:textColorDeselected="@color/white"
        app:indicatorPadding="8"
        app:indicatorMargin="8"
        app:indicatorSquare="true"
        app:indicatorTextSize="18"
        app:indicatorTextStyle="bold_italic"
        />

</RelativeLayout>
```

Per personalizzare l'indicatore si possono utilizzare alcuni parametri:

Abilita il click sull'indicatore, al click apre la pagina selezionata. 
```xml
    app:indicatorClickable="true"
```
Forza le dimensioni dell'indicatore. Con questo parametro avrà dimensioni quadrate.
```xml
    app:indicatorSquare="true"
```
Da utilizzare insieme, stabilisce il colore di sfondo che deve avere uando una pagina è selezionata e quando non lo è.
In alternativa si può utilizzare anche un drawable:
```xml
    app:backgroundColorDeselected="@color/black"
    app:backgroundColorSelected="@color/green"
```
```xml
    app:backgroundDrawableDeselected="@drawable/sfondo_pager_evento"
    app:backgroundDrawableSelected="@drawable/sfondo_pager_evento_selezionato"
```
Da utilizzare insieme, stabilisce il colore del testo che deve avere quando una pagina è selezionata e quando non lo è.
```xml
    app:textColorSelected="@color/blue"
    app:textColorDeselected="@color/white"
```
Definisce il padding interno di ogni singolo indicatore.
```xml
    app:indicatorPadding="8"
```

Definisce il margine tra un'indicatore e l'altro.
```xml
    app:indicatorMargin="8"
```
Applica la grandezza al testo. L'unità di misura sono gli sp.
```xml
    app:indicatorTextSize="18"
```
 Se si vuole utilizzare grandezze diverse per la pagina selezionata e quella deselezionata si può usare anche:
```xml
    app:indicatorTextSizeDeselected="18"
    app:indicatorTextSizeSelected="18"
```
Applica lo stile al testo. I valori che accetta sono:
bold, italic, normal, bold_italic
```xml
    app:indicatorTextStyle="bold_italic"
```
Se si vuole utilizzare stili diversi per la pagina selezionata e quella deselezionata si può usare anche:
```xml
    app:indicatorTextStyleDeselected="bold_italic"
    app:indicatorTextStyleSelected="bold_italic"
```

Se si vuole aggiungere un testo uguale in tutte le pagine si può utilizzare:
```xml
    app:indicatorText="P"
```

Se si vuole utilizzare un testo personalizzato per ogni pagina invece ci sono due modi. 
Se si vuole un testo diverso per la pagina selezionata e deselezionata si utilizza:

```java
    indicator.setSelectedText(selected);
    indicator.setDeselectedText(deselected);
```
Che accettano due liste di stringhe con i testi;

Altrimenti se il testo per la pagina selezionata e deselezionata è sempre lo stesso:
```java
    indicator.setLabelText(label);
```
Che accetta sempre una lista di stringhe.


Per inizializzare l'indicatore è necessario instanziarlo nell'Activity (o Fragment), impostare eventuali parametri e infine impostare il `ViewPager`.
Impostare il `ViewPager` è essenziale, altrimenti non funziona. Quando viene chiamato il metodo `setViewPager`, il ViewPager deve essere già stato instanziato e avere un Adapter.
Esempio:
```java
   BSDIndicator indicator = (BSDIndicator) findViewById(R.id.indicator);
   
   indicator.setTextColorDeselected(Color.WHITE);
        indicator.setTextColorSelected(Color.BLUE);
        indicator.setBackgroundColorSelected(Color.GREEN);
        indicator.setBackgroundColorDeselected(Color.BLACK);
        indicator.setPadding(8);
        indicator.setMargin(8);
        indicator.setIndicatorClickable(true);
        indicator.setLabelText(label);
        
        indicator.setViewPager(mPager);
```



Download
--------

Aggiungere al repository il percorso:
```groovy
repositories {
        jcenter()
        maven {
            url "http://dl.bintray.com/bsdsoftware/bsdsoftware"
        }
    }
```
e nel gradle file la dipendenza:
```groovy
compile 'it.bsdsoftware:bsd-viewpager-indicator:1.1'
```


