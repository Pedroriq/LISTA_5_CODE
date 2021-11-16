package br.inatel.cdg.main;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.Map.Entry;

public class Principal {

    public static void main(String[] args) throws IOException {

        Path caminhofunc = Paths.get ("funcionarios.csv");
        List <String> aux1 = new ArrayList<>();
        List <String> aux2 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<Integer,Integer> mapInt = new HashMap<>();
        StringBuilder filtroFeito = new StringBuilder();



        try {
            List<String> filtro = Files.readAllLines(caminhofunc);
            filtro.remove(0);
            filtro.forEach(linha -> {
                int auxInt = Integer.parseInt(linha.split(",")[3]);
               if(auxInt > 0){
                   aux1.add(linha);
                }
             });
        } catch (ConcurrentModificationException | IOException e) {
              e.printStackTrace();
          }

        Path novoArquivo = Paths.get("func_filtrado.csv");
        Files.writeString(novoArquivo, "Identificador,Filhos,Salario \n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            aux1.forEach(linha -> {
                filtroFeito.append((String)(linha.split(",")[0] + "," + linha.split(",")[3] + "," + linha.split(",")[4]));
                try {
                    Files.writeString(novoArquivo, filtroFeito.toString() + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                filtroFeito.setLength(0);

            });

    }
}
