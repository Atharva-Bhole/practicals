// #include<iostream>
// #include<sstream>
// #include<map>
// #include<fstream>
// #include<iomanip>
// #include<string>
// using namespace std;

// struct  Symbol{
//     string symbol;
//     int address;
// };

// struct Intermediate{
//     int address;
//     string label;
//     string opcode;
//     string operand;
// };

// map<string, int> symbolTable;
// map<string, string> opcodeTable = {
//     {"MOV", "01"},
//     {"ADD", "02"},
//     {"SUB", "03"},
//     {"BYTE", "04"},
//     {"WORD", "05"}
// };

// void pass1(string inputFile, string intermediateFile)
// {
//     ifstream in(inputFile);
//     ofstream out(intermediateFile);

//     string line;
//     int locationCounter = 0;
//     while(getline(in, line))
//     {
//         stringstream ss(line);
//         string label, opcode, operand;
//         ss>>label>>opcode>>operand;
//         if(opcode == "START")
//         {
//             locationCounter = stoi(operand);
//             out << locationCounter << "\t" << label << '\t' << opcode << '\t' << operand;
//             continue;
//         }

//         if(!label.empty())
//         {
//             symbolTable[label] = locationCounter;
//         }

//         out << locationCounter << '\t' << label<<"\t" << opcode << '\t' << operand;

//         if(opcode == "BYTE")
//         {
//             locationCounter += operand.length() - 3;
//         }
//         else if (opcode == "WORD")
//         {
//             locationCounter += 3;
//         }
//         else
//         {
//             locationCounter += 1;
//         }
//     }
//     in.close();
//     out.close();
// }







#include<iostream>
#include<sstream>
#include<iomanip>
#include<string>
#include<fstream>
#include<map>
using namespace std;

struct Symbol{
    string symbol;
    int address;
};

struct Intermediate{
    int address;
    string label;
    string opcode;
    string operand;
};

map<string, int> SymbolTable;
map<string, string> opcodeTable = {
    {"MOV", "01"},
    {"ADD", "02"},
    {"SUB", "03"},
    {"BYTE", "04"},
    {"WORD", "05"}
}

void pass1(string inputFile, string intermediateFile)
{
    ifstream in(inputFile);
    ofstream out(intermediateFile);
    string line;
    int locationCounter = 0;
    while(getline(in, line))
    {
        stringstream ss(line);
        string label, opcode, operand;
        ss >> label>>opcode>>operand;
        if(opcode == "START")
        {
            locationCounter = stoi(operand);
            out<<locationCounter << '\t' << label << '\t' << opcode << '\t' << operand;
            continue;
        }

        if(!label.empty())
        {
            SymbolTable[label] = locationCounter;
        }
        out<<locationCounter<<'\t'<<label<<'\t'<<opcode<<"\t"<<operand;
        if (opcode == "BYTE")
        {
            locationCounter += operand.length() - 3;
        }
        else if(opcode == "WORD")
        {
            locationCounter += 3;
        }
        else
        {
            locationCounter += 1;

        }
    }
    in.close();
    out.close();
}

// void pass2(string intermediateFile, string outputFile)
// {
//     ifstream in(intermediateFile);
//     ofstream out(outputFile);

//     string line;
//     while(getline(in, line))
//     {
//         stringstream ss;
//         int address;
//         string label, opcode, operand;
//         ss>>address>>label>>opcode>>operand;
//         string machineCode;
//         if(opcode == "BYTE")
//         {
//             machineCode = operand.substr(2, operand.length()-3);
//         }
//         else if(opcode == "WORD")
//         {
//             machineCode = string(6, operand.length(), '0') + operand;
//         }
//         else
//         {
//             machineCode = opcodeTable[opcode];
//             if(!operand.empty() && SymbolTable.find(operand) != SymbolTable.end())
//             {
//                 machineCode += to_string(SymbolTable[operand]);
//             }
//             else
//             {
//                 machineCode += "00";
//             }
//         }
//         out<<setw(4)<<setfill('0')<<address<<'\t'<<machineCode<<endl;
//     }
//     in.close();
//     out.close();
// }


void pass2(string intermediateFile, string outputFile)
{
    ifstream in(intermediateFile);
    ofstream out(outputFile);
    string line;
    while(getline(in, line))
    {
        stringstream ss(line);
        int address;
        string label, opcode, operand;
        ss>>address>>label>>opcode>>operand;
        string machineCode;
        if(opcode == "BYTE")
        {
            machineCode = operand.substr(2, operand.length()-3);
        }
        else if(opcode == "WORD")
        {
            machineCode = string(6, operand.length(), '0') + operand;
        }
        else
        {
            machineCode = opcodeTable[opcode];
            if(!operand.empty() && SymbolTable.find(operand) != SymbolTable.end())
            {
                machineCode += to_string(SymbolTable[operand]);
            }
            else
            {
                machineCode += "00";
            }
        }
        out<<setw(4)<<setfill('0')<<address<<'\t'<<machineCode<<endl;
    }
    in.close();
    out.close();
}

