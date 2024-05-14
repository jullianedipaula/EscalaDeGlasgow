package com.mycompany.escaladeglasgow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscalaDeGlasgow extends JFrame {
    private final JComboBox<Integer> grauOcular, grauVerbal, grauMotor;
    private final JLabel resultadoPontuacao, resultadoClassificacao;

    public EscalaDeGlasgow() {
        setTitle("Escala de Coma de Glasgow");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Integer[] pontuacaOcular = {1, 2, 3, 4};
        grauOcular = new JComboBox<>(pontuacaOcular);

        Integer[] pontuacaoVerbal = {1, 2, 3, 4, 5};
        grauVerbal = new JComboBox<>(pontuacaoVerbal);

        Integer[] pontuacaoMotora = {1, 2, 3, 4, 5, 6};
        grauMotor = new JComboBox<>(pontuacaoMotora);

        JButton calcButton = new JButton("Calcular Pontuação");
        calcButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calcButton.addActionListener(new CalcButtonListener());

        resultadoPontuacao = new JLabel("Pontuação Total: ");
        resultadoPontuacao.setFont(new Font("Arial", Font.BOLD, 14));
        resultadoPontuacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        resultadoClassificacao = new JLabel("Classificação: ");
        resultadoClassificacao.setFont(new Font("Arial", Font.BOLD, 14));
        resultadoClassificacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        // Layout para os campos de entrada
        JPanel painelDeEntrada = new JPanel(new GridLayout(4, 4));
        painelDeEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, -10, 10));
        painelDeEntrada.add(new JLabel("Resposta Ocular:"));
        painelDeEntrada.add(grauOcular);
        painelDeEntrada.add(new JLabel("Resposta Verbal:"));
        painelDeEntrada.add(grauVerbal);
        painelDeEntrada.add(new JLabel("Resposta Motora:"));
        painelDeEntrada.add(grauMotor);
        
        
        JPanel painelDoResultado = new JPanel();
        painelDoResultado.setLayout(new BoxLayout(painelDoResultado, BoxLayout.Y_AXIS));
        painelDoResultado.add(calcButton);
        painelDoResultado.add(Box.createVerticalStrut(20));
        painelDoResultado.add(resultadoPontuacao);
        painelDoResultado.add(resultadoClassificacao);

        // Adiciona os componentes ao frame
        setLayout(new BorderLayout());
        add(painelDeEntrada, BorderLayout.NORTH);
        add(painelDoResultado, BorderLayout.CENTER);
    }

    private class CalcButtonListener implements ActionListener {
       @Override
        public void actionPerformed(ActionEvent e) {
            // Obtém as pontuações das avaliações
            int ocular = (Integer) grauOcular.getSelectedItem();
            int verbal = (Integer) grauVerbal.getSelectedItem();
            int motor = (Integer) grauMotor.getSelectedItem();

            // Calcula o total e obtém a classificação correspondente
            int pontuacaoTotal = ocular + verbal + motor;
            String classificacao = pontuacaoGlasgow(pontuacaoTotal);

            // Atualiza o rótulo com a pontuação total e a classificação
            resultadoPontuacao.setText("Pontuação Total: " + pontuacaoTotal);
            resultadoClassificacao.setText("Classificação: " + classificacao);
        }
    }

    // Método que classifica a pontuação da escala de Glasgow
    private String pontuacaoGlasgow(int pontuacao) {
        if (pontuacao >= 13 && pontuacao <= 15) {
            return "Leve";
        } else if (pontuacao >= 9 && pontuacao <= 12) {
            return "Moderado";
        } else if (pontuacao >= 3 && pontuacao <= 8) {
            return "Grave";
        } else {
            return "Pontuação Inválida";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EscalaDeGlasgow app = new EscalaDeGlasgow();
            app.setVisible(true);
        });
    }
}
