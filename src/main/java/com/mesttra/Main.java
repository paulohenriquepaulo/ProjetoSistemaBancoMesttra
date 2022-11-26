package com.mesttra;

import com.mesttra.model.ClientePf;
import com.mesttra.model.ClientePj;
import com.mesttra.repository.ClienteRepository;
import com.mesttra.service.GerenteService;
import com.mesttra.service.MenuService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteRepository clientePJRepository = new ClienteRepository();

        Scanner entrada = new Scanner(System.in);

        while (true) {
            MenuService.menuPrincipal();
            int opcao = Integer.parseInt(entrada.nextLine());

            if (opcao == 1) {
                boolean repetir = true;
                while (repetir) {
                    MenuService.menuGerente();
                    switch (Integer.parseInt(entrada.nextLine())) {
                        case 1:
                            MenuService.cadastroCliente();
                            int tipoCliente = Integer.parseInt(entrada.nextLine());
                            if (tipoCliente == 2) {
                                ClientePf pf = (ClientePf) MenuService.dadosCliente(tipoCliente);
                                clientePJRepository.save(pf);
                                MenuService.dadosClientePF(pf);
                            } else {
                                ClientePj pj = (ClientePj) MenuService.dadosCliente(tipoCliente);
                                clientePJRepository.save(pj);
                            }
                            MenuService.menuGerente();
                            break;
                        case 2:
                            MenuService.deletarCliente();
                            break;
                        case 3:
                            GerenteService.buscarClientePf();
                            break;
                        case 4:
                            repetir = false;
                            break;

                    }
                }
            } else if (opcao == 2) {
                MenuService.menuCliente();

            } else {
                break;
            }
        }
    }
}