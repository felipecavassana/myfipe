package com.felipecavassana.myfipe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.felipecavassana.myfipe.client.RestApiService;
import com.felipecavassana.myfipe.model.CodeNameDTO;
import com.felipecavassana.myfipe.model.VehicleDetailsDTO;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FipeApiService {
    private final String URL = "https://fipe.parallelum.com.br/api/v2/";

    public Scanner lecture = new Scanner(System.in);
    public RestApiService restApiService = new RestApiService();
    public ConvertService convertService = new ConvertService();

    public void showMenu() {
        System.out.printf("Bem vindo a consulta FIPE API.\n" +
                "Para qual tipo de veículo você quer realizar a consulta?\n" +
                "1) Carro\n" +
                "2) Caminhão\n" +
                "3) Moto\n");
        System.out.printf("Digite o numero, ou o escreva o tipo do veículo: ");

        String vehicleType = lecture.nextLine();
        vehicleType = converteEscolha(vehicleType);

        String newUrl = URL + vehicleType + "/brands";
        var json = restApiService.get(newUrl);
        List<CodeNameDTO> vehicleBrandDTO = convertService.mapperList(json, CodeNameDTO.class);
        for (CodeNameDTO codeNameDTO : vehicleBrandDTO) {
            System.out.println(codeNameDTO);
        }

        System.out.printf("Digite o código da marca para ver os modelos: ");
        String brandCode = lecture.nextLine();

        newUrl += "/" + brandCode + "/models";
        json = restApiService.get(newUrl);
        List<CodeNameDTO> modelsBrandList = convertService.mapperObject(json, new TypeReference<List<CodeNameDTO>>() {});
        for (CodeNameDTO codeNameDTO : modelsBrandList) {
            System.out.println(codeNameDTO);
        }

        System.out.printf("Digite uma parte do nome do modelo para filtrar a lista: ");
        String namePart = lecture.nextLine();
        Set<CodeNameDTO> filteredModel = modelsBrandList.stream()
                .filter(p -> p.name().toUpperCase().contains(namePart.toUpperCase()))
                .collect(Collectors.toSet());
        for (CodeNameDTO codeNameDTO : filteredModel) {
            System.out.println(codeNameDTO);
        }

        System.out.printf("Digite o código do modelo para ver os valores por ano: ");
        String modelCode = lecture.nextLine();
        newUrl += "/" + modelCode + "/years";
        json = restApiService.get(newUrl);
        List<CodeNameDTO> yearsModelList = convertService.mapperObject(json, new TypeReference<List<CodeNameDTO>>() {});

        for (CodeNameDTO codeNameDTO : yearsModelList) {
            json = restApiService.get(String.format(newUrl +"/"+ codeNameDTO.code()));
            VehicleDetailsDTO vehicleDetailsDTO = convertService.mapperObject(json, VehicleDetailsDTO.class);
            System.out.println("Model: "+ vehicleDetailsDTO.model() +
                    "\t Year: "+ vehicleDetailsDTO.year() +
                    "\t Price: "+ vehicleDetailsDTO.price());
        }

    }

    private String converteEscolha(String escolha) {
        String returnWord = null;
        if (escolha.equalsIgnoreCase("1") || escolha.equalsIgnoreCase("carro")) {
            returnWord = "cars";
        } else if (escolha.equalsIgnoreCase("2") || escolha.equalsIgnoreCase("caminhão")) {
            returnWord = "trucks";
        } else if (escolha.equalsIgnoreCase("3") || escolha.equalsIgnoreCase("moto")) {
            returnWord = "motorcycles";
        }
        return returnWord;
    }

}
