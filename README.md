## Tentang
IdnLang merupakan interpreter sederhana dari program dengan berbahasa indonesia(file .id) ke java(sekaligus menjalankannya)

## Cara penggunaan
### Buat .jar dari source code
1. Pastikan telah menginstal maven di sistem operasi
2. jalankan command ``mvn generate-sources``
3. Jalankan command ``mvn clean install``
4. Jalankan comand ``bin\idnlang.bat src\test\java\dev\sch39\MyProgram.id`` untuk run code di file "MyProgram.id"

### Jalankan langsung dengan .jar di folder bin
1. Pastikan file .jar sudah ada saat meng-clone project ini
2. Jalankan comand ``bin\idnlang.bat src\test\java\dev\sch39\MyProgram.id`` untuk run code di file "MyProgram.id"