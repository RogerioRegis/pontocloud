# Ponto Cloud (mobile)
Um aplicativo móvel para registro eletrônico de ponto (REP-P).

## Recursos
- **Registro de Ponto:** Interface simples para registrar entradas de tempo.
- **Suporte Offline:** Simulação básica de registro de ponto offline.
- **Gerenciamento de Permissões:** Gerencia as permissões de Localização e Câmera necessárias para o registro seguro de tempo.

## Detalhes Técnicos
- **Linguagem:** Kotlin
- **Plataforma:** Android
- **Permissões Necessárias:**

- `ACCESS_FINE_LOCATION`: Para verificar a localização do ponto registrado.
- `CAMERA`: Para possível reconhecimento facial ou evidência fotográfica (planejado).
- `INTERNET`: Para sincronizar dados com a nuvem.

## Primeiros Passos
1. Abra o projeto no Android Studio.
2. Compile e execute o módulo `app` em um dispositivo Android ou emulador.
3. Conceda as permissões solicitadas.
4. Clique em "Bater Ponto" para registrar um tempo.

## Scripts
- `build-apk.sh` / `build-apk.bat`: Scripts para gerar o APK do aplicativo.
- `check-env.js`: Script de verificação do ambiente.