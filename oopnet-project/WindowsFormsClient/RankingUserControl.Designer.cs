
namespace WindowsFormsClient
{
    partial class RankingUserControl
    {
        /// <summary> 
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lblRanking = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.lblGoals = new System.Windows.Forms.Label();
            this.pbPicture = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pbPicture)).BeginInit();
            this.SuspendLayout();
            // 
            // lblRanking
            // 
            this.lblRanking.AutoSize = true;
            this.lblRanking.Location = new System.Drawing.Point(8, 9);
            this.lblRanking.Name = "lblRanking";
            this.lblRanking.Size = new System.Drawing.Size(35, 13);
            this.lblRanking.TabIndex = 0;
            this.lblRanking.Text = "label1";
            this.lblRanking.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(105, 10);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(35, 13);
            this.lblName.TabIndex = 1;
            this.lblName.Text = "label2";
            this.lblName.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblGoals
            // 
            this.lblGoals.AutoSize = true;
            this.lblGoals.Location = new System.Drawing.Point(294, 10);
            this.lblGoals.Name = "lblGoals";
            this.lblGoals.Size = new System.Drawing.Size(35, 13);
            this.lblGoals.TabIndex = 2;
            this.lblGoals.Text = "label3";
            this.lblGoals.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // pbPicture
            // 
            this.pbPicture.Location = new System.Drawing.Point(55, 3);
            this.pbPicture.Name = "pbPicture";
            this.pbPicture.Size = new System.Drawing.Size(29, 24);
            this.pbPicture.TabIndex = 3;
            this.pbPicture.TabStop = false;
            // 
            // RankingUserControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.pbPicture);
            this.Controls.Add(this.lblGoals);
            this.Controls.Add(this.lblName);
            this.Controls.Add(this.lblRanking);
            this.Name = "RankingUserControl";
            this.Size = new System.Drawing.Size(382, 30);
            ((System.ComponentModel.ISupportInitialize)(this.pbPicture)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lblRanking;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label lblGoals;
        private System.Windows.Forms.PictureBox pbPicture;
    }
}
