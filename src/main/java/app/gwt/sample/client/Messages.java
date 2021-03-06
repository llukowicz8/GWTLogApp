package app.gwt.sample.client;

import com.google.gwt.i18n.client.LocalizableResource.Generate;

@Generate(format = "com.google.gwt.i18n.server.PropertyCatalogFactory")
public interface Messages extends com.google.gwt.i18n.client.Messages {
  
  @DefaultMessage("Enter your name")
  String nameField();

  @DefaultMessage("Zaladuj plik")
  String loadButton();

  @DefaultMessage("Parsuj")
  String parseButton();

  @DefaultMessage("Tresc pliku")
  String contentHeader();

  @DefaultMessage("Wynik parsowania")
  String popUpHeader();

  @DefaultMessage("Zawartosc tabeli")
  String labelTable();

}
