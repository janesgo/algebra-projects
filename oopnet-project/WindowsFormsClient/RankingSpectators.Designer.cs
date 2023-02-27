
namespace WindowsFormsClient
{
    partial class RankingSpectators
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RankingSpectators));
            this.lblTeam = new System.Windows.Forms.Label();
            this.flpSpectators = new System.Windows.Forms.FlowLayoutPanel();
            this.lblVenue = new System.Windows.Forms.Label();
            this.lblSpectators = new System.Windows.Forms.Label();
            this.lblHomeTeam = new System.Windows.Forms.Label();
            this.lblAwayTeam = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // lblTeam
            // 
            resources.ApplyResources(this.lblTeam, "lblTeam");
            this.lblTeam.Name = "lblTeam";
            // 
            // flpSpectators
            // 
            resources.ApplyResources(this.flpSpectators, "flpSpectators");
            this.flpSpectators.Name = "flpSpectators";
            // 
            // lblVenue
            // 
            resources.ApplyResources(this.lblVenue, "lblVenue");
            this.lblVenue.Name = "lblVenue";
            // 
            // lblSpectators
            // 
            resources.ApplyResources(this.lblSpectators, "lblSpectators");
            this.lblSpectators.Name = "lblSpectators";
            // 
            // lblHomeTeam
            // 
            resources.ApplyResources(this.lblHomeTeam, "lblHomeTeam");
            this.lblHomeTeam.Name = "lblHomeTeam";
            // 
            // lblAwayTeam
            // 
            resources.ApplyResources(this.lblAwayTeam, "lblAwayTeam");
            this.lblAwayTeam.Name = "lblAwayTeam";
            // 
            // RankingSpectators
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lblAwayTeam);
            this.Controls.Add(this.lblHomeTeam);
            this.Controls.Add(this.lblSpectators);
            this.Controls.Add(this.lblVenue);
            this.Controls.Add(this.flpSpectators);
            this.Controls.Add(this.lblTeam);
            this.Name = "RankingSpectators";
            this.Load += new System.EventHandler(this.RankingSpectators_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label lblTeam;
        private System.Windows.Forms.FlowLayoutPanel flpSpectators;
        private System.Windows.Forms.Label lblVenue;
        private System.Windows.Forms.Label lblSpectators;
        private System.Windows.Forms.Label lblHomeTeam;
        private System.Windows.Forms.Label lblAwayTeam;
    }
}