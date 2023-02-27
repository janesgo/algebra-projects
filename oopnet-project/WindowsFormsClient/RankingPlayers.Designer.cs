
namespace WindowsFormsClient
{
    partial class RankingPlayers
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

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RankingPlayers));
            this.flpRanking = new System.Windows.Forms.FlowLayoutPanel();
            this.lblTeam = new System.Windows.Forms.Label();
            this.lblPlace = new System.Windows.Forms.Label();
            this.lblPicture = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.lblValue = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // flpRanking
            // 
            resources.ApplyResources(this.flpRanking, "flpRanking");
            this.flpRanking.Name = "flpRanking";
            // 
            // lblTeam
            // 
            resources.ApplyResources(this.lblTeam, "lblTeam");
            this.lblTeam.Name = "lblTeam";
            // 
            // lblPlace
            // 
            resources.ApplyResources(this.lblPlace, "lblPlace");
            this.lblPlace.Name = "lblPlace";
            // 
            // lblPicture
            // 
            resources.ApplyResources(this.lblPicture, "lblPicture");
            this.lblPicture.Name = "lblPicture";
            // 
            // lblName
            // 
            resources.ApplyResources(this.lblName, "lblName");
            this.lblName.Name = "lblName";
            // 
            // lblValue
            // 
            resources.ApplyResources(this.lblValue, "lblValue");
            this.lblValue.Name = "lblValue";
            // 
            // RankingPlayers
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lblValue);
            this.Controls.Add(this.lblName);
            this.Controls.Add(this.lblPicture);
            this.Controls.Add(this.lblPlace);
            this.Controls.Add(this.lblTeam);
            this.Controls.Add(this.flpRanking);
            this.Name = "RankingPlayers";
            this.Load += new System.EventHandler(this.Ranking_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.FlowLayoutPanel flpRanking;
        private System.Windows.Forms.Label lblTeam;
        private System.Windows.Forms.Label lblPlace;
        private System.Windows.Forms.Label lblPicture;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label lblValue;
    }
}