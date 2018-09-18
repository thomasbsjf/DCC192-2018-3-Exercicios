// Fig. 27.14: Rotator.Java
// Um JavaBean que faz rota��o de an�ncios.
package com.deitel.jhtp6.jsp.beans;

public class Rotator 
{
   private String images[] = { "illidan.jpg",
      "rise_lich_king.jpg", "shadows_of_the_horde.jpg"};
      
   private String links[] = {
      "https://www.amazon.com/Illidan-World-Warcraft-William-King/dp/0399177574/" ,
      "https://www.amazon.com/World-Warcraft-Arthas-Rise-Pocket/dp/143915760X/" ,
      "https://www.amazon.com/World-Warcraft-Voljin-Shadows-Horde/dp/1476702977/"};
         
   private int selectedIndex = 0;

   // retorna o nome do arquivo de imagem ao an�ncio atual
   public String getImage()
   {
      return images[ selectedIndex ];
   } // fim do m�todo getImage 

   // retorna o URL ao site Web correspondente ao an�ncio
   public String getLink()
   {
      return links[ selectedIndex ];
   } // fim do m�todo getLink 

   // atualiza selectedIndex assim as pr�ximas chamadas para getImage e
   // getLink retornam um an�ncio diferente
   public void nextAd()
   {
      selectedIndex = ( selectedIndex + 1 ) % images.length;
   } // fim do m�todo nextAd 
} // fim da classe Rotator 
