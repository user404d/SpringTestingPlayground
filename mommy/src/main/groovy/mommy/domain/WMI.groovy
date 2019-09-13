package mommy.domain

class WMI {
    static Map<String, String> lookup = [
            'AAV': 'Volkswagen South Africa',
            'AC5': 'Hyundai South Africa',
            'ADD': 'Hyundai South Africa',
            'AFA': 'Ford South Africa',
            'AHT': 'Toyota South Africa',
            'JA3': 'Mitsubishi',
            'JA4': 'Mitsubishi',
            'JA' : 'Isuzu',
            'JD' : 'Daihatsu',
            'JF' : 'Fuji Heavy Industries(Subaru)',
            'JH' : 'Honda',
            'JK' : 'Kawasaki(motorcycles)',
            'JL5': 'Mitsubishi Fuso',
            'JM1': 'Mazda',
            'JMB': 'Mitsubishi Motors',
            'JMY': 'Mitsubishi Motors',
            'JMZ': 'Mazda',
            'JN' : 'Nissan',
            'JS' : 'Suzuki',
            'JT' : 'Toyota',
            'JY' : 'Yamaha(motorcycles)',
            'KL' : 'Daewoo General Motors South Korea',
            'KM' : 'Hyundai',
            'KMY': 'Daelim(motorcycles)',
            'KM1': 'Hyosung(motorcycles)',
            'KN' : 'Kia',
            'KNM': 'Renault Samsung',
            'KPA': 'SsangYong',
            'KPT': 'SsangYong',
            'LAE': 'Jinan Qingqi Motorcycle',
            'LAN': 'Changzhou Yamasaki Motorcycle',
            'LBB': 'Zhejiang Qianjiang Motorcycle(Keeway / Generic)',
            'LBE': 'Beijing Hyundai',
            'LBM': 'Zongshen Piaggio',
            'LBP': 'Chongqing Jainshe Yamaha(motorcycles)',
            'LB2': 'Geely Motorcycles',
            'LCE': 'Hangzhou Chunfeng Motorcycles(CFMOTO)',
            'LDC': 'Dong Feng Peugeot Citroen(DPCA) , China',
            'LDD': 'Dandong Huanghai Automobile',
            'LDF': 'Dezhou Fulu Vehicle(motorcycles)',
            'LDN': 'SouEast Motor',
            'LDY': 'Zhongtong Coach , China',
            'LET': 'Jiangling-Isuzu Motors, China',
            'LE4': 'Beijing Benz , China',
            'LFB': 'FAW, China (busses)',
            'LFG': 'Taizhou Chuanl Motorcycle Manufacturing',
            'LFP': 'FAW, China (passenger vehicles )',
            'LFT': 'FAW, China (trailers)',
            'LFV': 'FAW Volkswagen, China',
            'LFW': 'FAW JieFang , China',
            'LFY': 'Changshu Light Motorcycle Factory',
            'LGB': 'Dong Feng(DFM) , China',
            'LGH': 'Qoros(formerly Dong Feng (DFM) ), China',
            'LGX': 'BYD Auto , China',
            'LHB': 'Beijing Automotive Industry Holding',
            'LH1': 'FAW-Haima, China',
            'LJC': 'JAC, China',
            'LJ1': 'JAC, China',
            'LKL': 'Suzhou King Long, China',
            'LL6': 'Hunan Changfeng Manufacture Joint - Stock',
            'LL8': 'Linhai(ATV)',
            'LMC': 'Suzuki Hong Kong(motorcycles)',
            'LPR': 'Yamaha Hong Kong(motorcycles)',
            'LSG': 'Shanghai General Motors, China',
            'LSJ': 'MG Motor UK Limited - SAIC Motor, Shanghai, China',
            'LSV': 'Shanghai Volkswagen , China',
            'LSY': 'Brilliance Zhonghua',
            'LTV': 'Toyota Tian Jin',
            'LUC': 'Guangqi Honda , China',
            'LVS': 'Ford Chang An',
            'LVV': 'Chery, China',
            'LVZ': 'Dong Feng Sokon Motor Company(DFSK)',
            'LZM': 'MAN China',
            'LZE': 'Isuzu Guangzhou , China',
            'LZG': 'Shaanxi Automobile Group, China',
            'LZP': 'Zhongshan Guochi Motorcycle(Baotian)',
            'LZY': 'Yutong Zhengzhou , China',
            'LZZ': 'Chongqing Shuangzing Mech & Elec(Howo)',
            'L4B': 'Xingyue Group(motorcycles)',
            'L5C': 'KangDi(ATV)',
            'L5K': 'Zhejiang Yongkang Easy Vehicle',
            'L5N': 'Zhejiang Taotao , China(ATV & motorcycles)',
            'L5Y': 'Merato Motorcycle Taizhou Zhongneng',
            'L85': 'Zhejiang Yongkang Huabao Electric Appliance',
            'L8X': 'Zhejiang Summit Huawin Motorcycle',
            'MAB': 'Mahindra & Mahindra',
            'MAC': 'Mahindra & Mahindra',
            'MAJ': 'Ford India',
            'MAK': 'Honda Siel Cars India',
            'MAL': 'Hyundai',
            'MAT': 'Tata Motors',
            'MA1': 'Mahindra & Mahindra',
            'MA3': 'Suzuki India(Maruti)',
            'MA6': 'GM India',
            'MA7': 'Mitsubishi India(formerly Honda)',
            'MBH': 'Suzuki India(Maruti)',
            'MBJ': 'Toyota India',
            'MBR': 'Mercedes-Benz India',
            'MB1': 'Ashok Leyland',
            'MCA': 'Fiat India',
            'MCB': 'GM India',
            'MC2': 'Volvo Eicher commercial vehicles limited.',
            'MDH': 'Nissan India'

    ]

    public static String get(String vin) {
        sleep(200)
        if (vin.length() < 3) {
            return 'UNKNOWN'
        } else {
            return lookup[vin[0..2]] ?: lookup[vin[0..1]] ?: "UNKNOWN"
        }
    }

//    MD2 Bajaj
//    Auto
//    MD9 Shuttle
//    Cars India
//    MEE Renault
//    India
//    MEX Volkswagen
//    India
//    MHF Toyota
//    Indonesia
//    MHR Honda
//    Indonesia
//    MLC Suzuki
//    Thailand
//    MLH Honda
//    Thailand
//    MMB Mitsubishi
//    Thailand
//    MMC Mitsubishi
//    Thailand
//    MMM Chevrolet
//    Thailand
//    MMS Suzuki
//    Thailand
//    MMT Mitsubishi
//    Thailand
//    MM8 Mazda
//    Thailand
//    MNB Ford
//    Thailand
//    MNT Nissan
//    Thailand
//    MPA Isuzu
//    Thailand
//    MP1 Isuzu
//    Thailand
//    MRH Honda
//    Thailand
//    MR0 Toyota
//    Thailand
//    MS0 KIA
//    Myanmar
//    MS3 Suzuki
//    Myanmar Motor
//    Co.,
//    Ltd.
//            NLA Honda
//    Türkiye
//    NLE Mercedes
//    -Benz Türk Truck
//    NLH Hyundai
//    Assan
//    NLT TEMSA
//    NMB Mercedes
//    -Benz Türk Buses
//    NMC BMC
//    NM0 Ford
//    Turkey
//    NM4 Tofaş
//    Türk
//    NMT Toyota
//    Türkiye
//    NNA Isuzu
//    Turkey
//    PE1 Ford
//    Philippines
//    PE3 Mazda
//    Philippines
//    PL1 Proton, Malaysia
//    PNA NAZA, Malaysia
//    (Peugeot)
//    R2P Evoke
//    Electric Motorcycles
//    HK
//    RA1 Steyr
//    Trucks International
//    FZE , UAE
//    RFB Kymco, Taiwan
//    RFG Sanyang
//    SYM , Taiwan
//    RFL Adly, Taiwan
//    RFT CPI, Taiwan
//    RF3 Aeon
//    Motor , Taiwan
//    SAB Optare
//    SAD Jaguar(F - Pace, I - Pace)
//    SAL Land
//    Rover
//    SAJ Jaguar
//    SAR Rover
//    SAX Austin
//    -Rover
//    SB1 Toyota
//    UK
//    SBM McLaren
//    SCA Rolls
//    Royce
//    SCB Bentley
//    SCC Lotus
//    Cars
//    SCE DeLorean
//    Motor Cars
//    N.Ireland(UK)
//    SCF Aston
//    SDB Peugeot
//    UK(formerly Talbot)
//    SED General
//    Motors Luton
//    Plant
//    SEY LDV
//    SFA Ford
//    UK
//    SFD Alexander
//    Dennis UK
//    SHH Honda
//    UK
//    SHS Honda
//    UK
//    SJN Nissan
//    UK
//    SKF Vauxhall
//    SLP JCB
//    Research UK
//    SMT Triumph
//    Motorcycles
//    SUF Fiat
//    Auto Poland
//    SUL FSC(Poland)
//    SUP FSO
//    -Daewoo(Poland)
//    SUU Solaris
//    Bus & Coach(Poland)
//    SWV TA
//    -NO(Poland)
//    TCC Micro
//    Compact Car
//    AG(smart 1998 - 1999)
//    TDM QUANTYA
//    Swiss Electric
//    Movement(Switzerland)
//    TK9 SOR
//    buses(Czech Republic)
//    TMA Hyundai
//    Motor Manufacturing
//    Czech
//    TMB Škoda(Czech Republic)
//    TMK Karosa(Czech Republic)
//    TMP Škoda
//    trolleybuses(Czech Republic)
//    TMT Tatra(Czech Republic)
//    TM9 Škoda
//    trolleybuses(Czech Republic)
//    TNE TAZ
//    TN9 Karosa(Czech Republic)
//    TRA Ikarus
//    Bus
//    TRU Audi
//    Hungary
//    TSB Ikarus
//    Bus
//    TSE Ikarus
//    Egyedi Autobuszgyar,
//    (Hungary)
//    TSM Suzuki
//    Hungary
//    TW1 Toyota
//    Caetano Portugal
//    TYA Mitsubishi
//    Trucks Portugal
//    TYB Mitsubishi
//    Trucks Portugal
//    UU1 Renault
//    Dacia , (Romania)
//    UU3 ARO
//    UU6 Daewoo
//    Romania
//    U5Y Kia
//    Motors Slovakia
//    U6Y Kia
//    Motors Slovakia
//    VAG Magna
//    Steyr Puch
//    VAN MAN
//    Austria
//    VBK KTM(Motorcycles)
//    VF1 Renault
//    VF2 Renault
//    VF3 Peugeot
//    VF4 Talbot
//    VF6 Renault(Trucks & Buses)
//    VF7 Citroën
//    VF8 Matra
//    VF9 / 795 Bugatti
//    VG5 MBK(motorcycles)
//    VLU Scania
//    France
//    VN1 SOVAB(France)
//    VNE Irisbus(France)
//    VNK Toyota
//    France
//    VNV Renault
//    -Nissan
//    VSA Mercedes
//    -Benz Spain
//    VSE Suzuki
//    Spain(Santana Motors)
//    VSK Nissan
//    Spain
//    VSS SEAT
//    VSX Opel
//    Spain
//    VS6 Ford
//    Spain
//    VS7 Citroën
//    Spain
//    VS9 Carrocerias
//    Ayats(Spain)
//    VTH Derbi(motorcycles)
//    VTL Yamaha
//    Spain(motorcycles)
//    VTT Suzuki
//    Spain(motorcycles)
//    VV9 TAURO
//    Spain
//    VWA Nissan
//    Spain
//    VWV Volkswagen
//    Spain
//    VX1 Zastava
//    /
//    Yugo Serbia
//    WAG Neoplan
//    WAU Audi
//    WA1 Audi
//    SUV
//    WBA BMW
//    WBS BMW
//    M
//    WBW BMW
//    WBY BMW
//    WDA Daimler
//    WDB Mercedes
//    -Benz
//    WDC DaimlerChrysler
//    WDD Mercedes
//    -Benz
//    WDF Mercedes
//    -Benz(commercial vehicles)
//    WEB Evobus
//    GmbH(Mercedes - Bus)
//    WJM Iveco
//    Magirus
//    WF0 Ford
//    Germany
//    WKE Fahrzeugwerk Bernard Krone(truck trailers)
//    WKK Kässbohrer
//    / Setra
//    WMA MAN
//    Germany
//    WME smart
//    WMW MINI
//    WMX Mercedes
//    -AMG
//    WP0 Porsche
//    WP1 Porsche
//    SUV
//    WSM Schmitz
//    -Cargobull(truck trailers)
//    W09 RUF
//    W0L Opel
//    W0V Opel(since 2017)
//    WUA Audi Sport GmbH(formerly quattro GmbH)
//    WVG Volkswagen
//    MPV / SUV
//    WVW Volkswagen
//    WV1 Volkswagen
//    Commercial Vehicles
//    WV2 Volkswagen
//    Bus / Van
//    WV3 Volkswagen
//    Trucks
//    XLB Volvo(NedCar)
//    XLE Scania
//    Netherlands
//    XLR DAF(trucks)
//    XL9 / 363 Spyker
//    XMC Mitsubishi(NedCar)
//    XTA Lada
//    / AvtoVAZ(Russia)
//    XTC KAMAZ(Russia)
//    XTH GAZ(Russia)
//    XTT UAZ
//    / Sollers(Russia)
//    XTU Trolza(Russia)
//    XTY LiAZ(Russia)
//    XUF General
//    Motors Russia
//    XUU AvtoTor(Russia, General Motors SKD)
//    XW8 Volkswagen
//    Group Russia
//    XWB UZ
//    -Daewoo(Uzbekistan)
//    XWE AvtoTor(Russia, Hyundai - Kia SKD)
//    X1M PAZ(Russia)
//    X4X AvtoTor(Russia, BMW SKD)
//    X7L Renault
//    AvtoFramos(Russia)
//    X7M Hyundai
//    TagAZ(Russia)
//    YBW Volkswagen
//    Belgium
//    YB1 Volvo
//    Trucks Belgium
//    YCM Mazda
//    Belgium
//    YE2 Van
//    Hool(buses)
//    YH2 BRP
//    Finland(Lynx snowmobiles)
//    YK1 Saab
//    -Valmet Finland
//    YS2 Scania
//    AB
//    YS3 Saab
//    YS4 Scania
//    Bus
//    YTN Saab
//    NEVS
//    YT9 / 007 Koenigsegg
//    YT9 / 034 Carvia
//    YU7 Husaberg(motorcycles)
//    YV1 Volvo
//    Cars
//    YV4 Volvo
//    Cars
//    YV2 Volvo
//    Trucks
//    YV3 Volvo
//    Buses
//    Y3M MAZ(Belarus)
//    Y6D Zaporozhets
//    / AvtoZAZ(Ukraine)
//    ZAA Autobianchi
//    ZAM Maserati
//    ZAP Piaggio
//    / Vespa / Gilera
//    ZAR Alfa
//    Romeo
//    ZBN Benelli
//    ZCG Cagiva
//    SpA / MV Agusta
//    ZCF Iveco
//    ZDM Ducati
//    Motor Holdings
//    SpA
//    ZDF Ferrari
//    Dino
//    ZD0 Yamaha
//    Italy
//    ZD3 Beta
//    Motor
//    ZD4 Aprilia
//    ZFA Fiat
//    ZFC Fiat
//    V.I.
//            ZFF Ferrari
//    ZGU Moto
//    Guzzi
//    ZHW Lamborghini
//    ZJM Malaguti
//    ZJN Innocenti
//    ZKH Husqvarna
//    Motorcycles Italy
//    ZLA Lancia
//    Z8M Marussia(Russia)
//    1 B3 Dodge
//    1 C3 Chrysler
//    1 C4 Chrysler
//    1 C6 Chrysler
//    1D 3 Dodge
//    1F A Ford Motor Company
//    1F B Ford Motor Company
//    1F C Ford Motor Company
//    1F D Ford Motor Company
//    1F M Ford Motor Company
//    1F T Ford Motor Company
//    1F U Freightliner
//    1F V Freightliner
//    1F 9 FWD Corp.
//    1G General Motors USA
//    1G C Chevrolet Truck USA
//    1G T GMC Truck USA
//    1G 1 Chevrolet USA
//    1G 2 Pontiac USA
//    1G 3 Oldsmobile USA
//    1G 4 Buick USA
//    1G 6 Cadillac USA
//    1G 8 Saturn USA
//    1G M Pontiac USA
//    1G Y Cadillac USA
//    1 H Honda USA
//    1 HD Harley -Davidson
//    1 HT International Truck and Engine Corp . USA
//    1J4 Jeep
//    1 J8 Jeep
//    1L Lincoln USA
//    1 ME Mercury USA
//    1 M1 Mack Truck USA
//    1 M2 Mack Truck USA
//    1 M3 Mack Truck USA
//    1 M4 Mack Truck USA
//    1 M9 Mynatt Truck & Equipment
//    1 N Nissan USA
//    1 NX NUMMI USA
//    1 P3 Plymouth USA
//    1 R9 Roadrunner Hay Squeeze USA
//    1 VW Volkswagen USA
//    1 XK Kenworth USA
//    1 XP Peterbilt USA
//    1 YV Mazda USA(AutoAlliance International)
//    1 ZV Ford(AutoAlliance International)
//    2 A4 Chrysler Canada
//    2 BP Bombardier Recreational Products
//    2 B3 Dodge Canada
//    2 B7 Dodge Canada
//    2 C3 Chrysler Canada
//    2 CN CAMI
//    2D 3 Dodge Canada
//    2F A Ford Motor Company Canada
//    2F B Ford Motor Company Canada
//    2F C Ford Motor Company Canada
//    2F M Ford Motor Company Canada
//    2F T Ford Motor Company Canada
//    2F U Freightliner
//    2F V Freightliner
//    2F Z Sterling
//    2G x General Motors Canada
//    2G 1 Chevrolet Canada
//    2G 2 Pontiac Canada
//    2G 3 Oldsmobile Canada
//    2G 4 Buick Canada
//    2G 9 mfr . of less than 1000 / yr.Canada
//    2 HG Honda Canada
//    2 HK Honda Canada
//    2 HJ Honda Canada
//    2 HM Hyundai Canada
//    2 M Mercury
//    2 NV Nova Bus Canada
//    2 P3 Plymouth Canada
//    2 T Toyota Canada
//    2 TP Triple E Canada LTD
//    2 V4 Volkswagen Canada
//    2 V8 Volkswagen Canada
//    2 WK Western Star
//    2 WL Western Star
//    2 WM Western Star
//    3 C4 Chrysler Mexico
//    3D 3 Dodge Mexico
//    3D 4 Dodge Mexico
//    3F A Ford Motor Company Mexico
//    3F E Ford Motor Company Mexico
//    3G General Motors Mexico
//    3 H Honda Mexico
//    3 JB BRP Mexico(all - terrain vehicles)
//    3 MD Mazda Mexico
//    3 MZ Mazda Mexico
//    3 N Nissan Mexico
//    3 NS Polaris Industries USA
//    3 NE Polaris Industries USA
//    3 P3 Plymouth Mexico
//    3 VW Volkswagen Mexico
//    46 J Federal Motors Inc . USA
//    4 EN Emergency One USA
//    4F Mazda USA
//    4 JG Mercedes -Benz USA
//    4 M Mercury
//    4 P1 Pierce Manufacturing Inc . USA
//    4 RK Nova Bus USA
//    4 S Subaru -Isuzu Automotive
//    4 T Toyota
//    4 T9 Lumen Motors
//    4 UF Arctic Cat Inc .
//    4 US BMW USA
//    4 UZ Frt -Thomas Bus
//    4 V1 Volvo
//    4 V2 Volvo
//    4 V3 Volvo
//    4 V4 Volvo
//    4 V5 Volvo
//    4 V6 Volvo
//    4 VL Volvo
//    4 VM Volvo
//    4 VZ Volvo
//    538 Zero Motorcycles(USA)
//    5F Honda USA -Alabama
//    5 J Honda USA - Ohio
//    5L Lincoln
//    5 N1 Nissan USA
//    5 NP Hyundai USA
//    5 T Toyota USA - trucks
//    5 YJ Tesla , Inc.
//    56 K Indian Motorcycle USA
//    6 AB MAN Australia
//    6F 4 Nissan Motor Company Australia
//    6F 5 Kenworth Australia
//    6F P Ford Motor Company Australia
//    6G 1 General Motors - Holden(post Nov 2002)
//    6G 2 Pontiac Australia(GTO & G8)
//    6 H8 General Motors - Holden(pre Nov 2002)
//    6 MM Mitsubishi Motors Australia
//    6 T1 Toyota Motor Corporation Australia
//    6 U9 Privately Imported car in Australia
//    8 AD Peugeot Argentina
//    8 AF Ford Motor Company Argentina
//    8 AG Chevrolet Argentina
//    8 AJ Toyota Argentina
//    8 AK Suzuki Argentina
//    8 AP Fiat Argentina
//    8 AW Volkswagen Argentina
//    8 A1 Renault Argentina
//    8G D Peugeot Chile
//    8G G Chevrolet Chile
//    8L D Chevrolet Ecuador
//    935 Citroën Brazil
//    936 Peugeot Brazil
//    93 H Honda Brazil
//    93 R Toyota Brazil
//    93 U Audi Brazil
//    93 V Audi Brazil
//    93 X Mitsubishi Motors Brazil
//    93 Y Renault Brazil
//    94D Nissan Brazil
//    9 BF Ford Motor Company Brazil
//    9 BG Chevrolet Brazil
//    9 BM Mercedes -Benz Brazil
//    9 BR Toyota Brazil
//    9 BS Scania Brazil
//    9 BW Volkswagen Brazil
//    9F B Renault Colombia
//    WB1 BMW
//    Motorrad of
//    North America
//}
}
