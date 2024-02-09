package faturamento;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FaturamentoPercentual {
    public static void main(String[] args) throws ParseException {
        String[] faturamentos = {
                "SP – R$67.836,43",
                "RJ – R$36.678,66",
                "MG – R$29.229,88",
                "ES – R$27.165,48",
                "Outros – R$19.849,53"
        };

        Locale defaultLocale = Locale.getDefault(); 
        NumberFormat numberFormat = NumberFormat.getNumberInstance(defaultLocale);
        double totalFaturamento = 0;
        Map<String, Double> faturamentoPorEstado = new HashMap<>();

        for (String faturamento : faturamentos) {
            String[] partes = faturamento.split(" – ");
            String estado = partes[0];
            String valorStr = partes[1].replaceAll("[^0-9,]", ""); 
            valorStr = valorStr.replace(",", ".");
            double valor = numberFormat.parse(valorStr).doubleValue();
            faturamentoPorEstado.put(estado, valor);
            totalFaturamento += valor;
        }

        for (Map.Entry<String, Double> estado : faturamentoPorEstado.entrySet()) {
            double percentual = (estado.getValue() / totalFaturamento) * 100;
            System.out.printf("%s: %.2f%%\n", estado.getKey(), percentual);
        }
    }
}

