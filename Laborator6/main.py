import os

class GenericFile:
    def get_path(self): raise NotImplementedError("message")
    def get_freq(self): raise NotImplementedError("message")

class TextASCII(GenericFile):
    def __init__(self, path_absolut, frecvente):
        self.path_absolut = path_absolut
        self.frecvente = frecvente

    def get_path(self): 
        return self.path_absolut
    
    def get_freq(self): 
        return self.frecvente


class TextUNICODE(GenericFile):
    def __init__(self, path_absolut, frecvente):
        self.path_absolut = path_absolut
        self.frecvente = frecvente

    def get_path(self): 
        return self.path_absolut
    
    def get_freq(self): 
        return self.frecvente


class Binary(GenericFile):
    def __init__(self, path_absolut, frecvente):
        self.path_absolut = path_absolut
        self.frecvente = frecvente

    def get_path(self): 
        return self.path_absolut
    
    def get_freq(self): 
        return self.frecvente


class XMLFile(TextASCII):
    def __init__(self, path_absolut, frecvente, first_tag):
        super().__init__(path_absolut, frecvente)
        self.first_tag = first_tag
        
    def get_first_tag(self):
        return self.first_tag

class BMP(Binary):
    def __init__(self, path_absolut, frecvente, width, height, bpp):
        super().__init__(path_absolut, frecvente)
        self.width = width
        self.height = height
        self.bpp = bpp
        
    def show_info(self):
        return f"{self.get_path()} | dimensiune: ({self.width},{self.height}) | bpp: {self.bpp}"


def calculeaza_frecvente(content):
    frecv = [0] * 256
    for byte in content:
        frecv[byte] += 1
    return frecv

def clasifica_fisier(path_absolut, content):
    if not content:
        return None 
        
    total_bytes = len(content)
    frecvente = calculeaza_frecvente(content)
    
    #UNICODE
    if frecvente[0] / total_bytes >= 0.30:
        return TextUNICODE(path_absolut, frecvente)
        
    #ASCII și XML
    octeti_valizi = sum(frecvente[i] for i in range(32, 128)) + frecvente[9] + frecvente[10] + frecvente[13]
    if octeti_valizi / total_bytes > 0.95: 
        try:
            text = content.decode('ascii', errors='ignore').strip()
            if text.startswith('<'):
                end_idx = text.find('>')
                if end_idx != -1:
                    first_tag = text[:end_idx+1]
                    return XMLFile(path_absolut, frecvente, first_tag)
        except:
            pass 
        return TextASCII(path_absolut, frecvente)
        
    #BMP și Binar
    if content.startswith(b'BM') and total_bytes >= 54:
        width = int.from_bytes(content[18:22], byteorder='little')
        height = int.from_bytes(content[22:26], byteorder='little')
        bpp = int.from_bytes(content[28:30], byteorder='little')
        return BMP(path_absolut, frecvente, width, height, bpp)
        
    return Binary(path_absolut, frecvente)


def analizeaza_director_recursiv():
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))
    
    lista_xml = []
    lista_unicode = []
    lista_bmp = []
    
    for root, subdirs, files in os.walk(ROOT_DIR):
        for file in files: 
            file_path = os.path.join(root, file)
            
            if os.path.isfile(file_path):
                f = open(file_path, 'rb')
                try:
                    content = f.read()
                    obiect = clasifica_fisier(file_path, content)
                    
                    if type(obiect) is XMLFile:
                        lista_xml.append(obiect)
                    else:
                        if type(obiect) is TextUNICODE:
                            lista_unicode.append(obiect)
                        else:
                            if type(obiect) is BMP:
                                lista_bmp.append(obiect)
                        
                finally:
                    f.close()

    print("XML, ASCII: ")
    for xml in lista_xml:
        print("[" + xml.get_first_tag() + "] -> " + xml.get_path())

    print("\n UNICODE: ")
    for unic in lista_unicode:
        print(unic.get_path())

    print("\n BMP: ")
    for bmp in lista_bmp:
        print(bmp.show_info())

if __name__ == "__main__":
    analizeaza_director_recursiv()